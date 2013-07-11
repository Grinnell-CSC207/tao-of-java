package ushahidi;

/*Ushahidi Web API commands were found at:
 * https://wiki.ushahidi.com/display/WIKI/Ushahidi+Public+API
 * https://wiki.ushahidi.com/display/WIKI/Ushahidi+Admin+API*/

import java.io.*;

import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class API {
	private ArrayList<Incident> incidentsList = new ArrayList<Incident>();
	private int index;
	private String server;
	private int maxId = 0;

	/**
	 * 
	 * @param server
	 * 	a URL string that can be used to go to a web page containing JSON text
	 * @throws Exception
	 * 	When the given server URL is empty.
	 * @pre 
	 * 	The constructor must be given a valid http URL. It will run update() so that it can collect the incidents from the 
	 * 	given URL and add all of them to the ArrayList incidentsList.
	 * @post
	 * 	The result of the constructor will be the initialization of the fields index and server and the ArrayList 
	 * 	incidentsList containing all of the Incidents from the URL. 	 
	 */
	public API(String server) 
			throws Exception {
		if(server == null)
			throw new Exception("Sever URL cannot be empty");
		this.server = server;
		this.index = 0;
		update();
	} // Ushahidi(String)
	
	/**
	 * 
	 * @throws Exception
	 * 	In the case that the server is not given a valid URL the method will throw an exception.
	 * @pre 
	 *  update() can be called if the field server has be initialized with a valid URL that contains JSON text. 
	 *  This JSON text must be obtained by using the Ushahidi web API. In addition to that, this method expects
	 *  that all of the Incidents are complete, that all of their fields have valid values.
	 * @post
	 * 	This method will end with the array list, called incidentsList, containing a number of 
	 * 	incident objects equals to the number of the incidents the given source has. This number is not limited
	 * 	to the 20 incidents the the web API is able to show. Each incident object declared will match the incident
	 * 	that is corresponds to.
	 */
	public void update() 
			throws Exception {

		JSONObject JSONText;
		JSONObject JSONErrorCodes; 
		String errorNumber;

		do {
			URL serverURL;
			
			// Test to see if maxId is 0, which would mean this is the first time this method has been ran.
			if (this.maxId == 0) 
				serverURL = new URL(this.server + "/api?task=incidents&by=all&limit=5000");
			// If maxId is not 0, then this must have been called before.
			else
				serverURL = new URL(this.server + "/api?task=incidents&by=sinceid&id=" + this.maxId);
			
			BufferedReader incidentParser;
			String data = "";

			if (0 == this.server.substring(0, 4).compareTo("https")) {
				HttpsURLConnection connection = (HttpsURLConnection)serverURL.openConnection();
			
				// Read in all of the data from the  server.
				incidentParser = 
						new BufferedReader(
								new InputStreamReader(connection.getInputStream()));
			} // if (0 == this.server.substring(0, 4).compareTo("https"))
			else{
				incidentParser = 
						new BufferedReader(
								new InputStreamReader(
										serverURL.openStream()));
			} // else
			
			String inputLine;
			while ((inputLine  = incidentParser.readLine()) != null) {
				data += inputLine;
			} // while (inptuLine != null)

			JSONText = new JSONObject(data.substring(data.indexOf("{")));			
			JSONErrorCodes = new JSONObject(data.substring(data.indexOf("{")));

			errorNumber = (String) JSONErrorCodes.getJSONObject("error").get("code");
			if (!errorNumber.contentEquals("007")) {
				JSONText = (JSONObject) JSONText.get("payload");
				JSONArray array = JSONText.getJSONArray("incidents"); 
				for (int i = 0; i< array.length(); i++) {
					Incident newIncident = new Incident((JSONObject) array.get(i));
					this.incidentsList.add(newIncident);
					if(newIncident.getIncidentId() > this.maxId)
						this.maxId = newIncident.getIncidentId();
				} // for (int i = 0; i< array.length(); i++)
			} // if	(!errorNumber.contentEquals("007"))
		}
		while(!errorNumber.contentEquals("007"));
	} // update()

	/**
	 * 
	 * @return
	 * 	an Incident object
	 * @throws Exception
	 * 	In the case of there being no other Incidents, in the array list, at or after the current index.
	 * @pre
	 * 	nextIncident() will update the list of Incidents and check that there are still Incidents after the 
	 * 	current Incident in the array list and, if there are, it will return the next incident in the 
	 * 	array list. In addition to that it will also increment the index. 
	 * @post
	 * 	The result of this method will only ever be an Incident object at the current index of 
	 * 	the array list. The only other outcome of nextIncident() will be an thrown Exception. 
	 */
	public Incident nextIncident() 
			throws Exception {
		update();
		if (this.incidentsList.size() > index) {
			return this.incidentsList.get(index++);
		} // if (this.incidentsList.size() > index)
		else {
			this.index = 0;
			throw new Exception("No new Incidents");
		} // else
	} // nextIncident()

	/**
	 * 
	 * @return
	 * 	an Incident object
	 * @throws Exception
	 * 	In the case of there being no other Incidents, in the array list, at or before the current index.
	 * @pre
	 * 	prevIncident() will update the list of Incidents check that there are still Incidents 
	 * 	before the current Incident in the array list and, if there are, it will return the 
	 * 	preceding incident in the array list.
	 * @post
	 * 	The result of this method will only ever be an Incident object at the current index of the array
	 * 	list. The only other outcome of prevIncident() will be a thrown Exception.
	 */
	public Incident prevIncident() 
			throws Exception {
		update();
		if (index > 0 && this.incidentsList.size() > 0) {
			return this.incidentsList.get(--index);
		} // if (index >= 0)
		else {
			this.index = 0;
			throw new Exception("No previous Incidents");
		} // else
	} // prevIncident()

	/*///////////////////////////////////////////////////////////////////////////// 
	 * ALL DELETE METHODS ARE INCOMPLETE!!!									     //
	 * ALL NEED TO DELETE THE APPROPIATE INCIDENT FROM THE WEB API 				 //	
	 * Need to figure out how to send a &action=delete&incident_id=1 GET request //
	 *///////////////////////////////////////////////////////////////////////////*/					
	
	/**
	 * 
	 * @return
	 * 	the deleted Incident 
	 * @throws Exception
	 * 	When there are no Incidents left in the ArrayList.
	 * @pre
	 * 	deleteIncident() will remove an Incident, at the current index, from the ArrayIncident incidentsList. 
	 * 	After it removed the Incident it will return the removed Incident. 
	 * @post
	 * 	The result of deleteIncident() will be an arrayList with one less incident. In the case that there
	 * 	no Incidents in the ArrayList deleteIncident() will throw an Exception. 
	 */
	public Incident deleteIncident() 
			throws Exception {
		update();
		if (index >= 0 && incidentsList.size() > index){
			Incident temp = this.incidentsList.get(index);
			// Connect to URL with the delete GET request
			//URL serverURL = new URL(this.server + "&action=delete&incident_id=" + this.index);
			//URLConnection URLConnection = serverURL.openConnection();
			//URLConnection.connect();
			this.incidentsList.remove(index);
			return temp;
		} // if
		else{
			this.index = 0;
			throw new Exception("No Incidents left in ArrayList");
		} // else
	} //deleteIncident()
	
	/**
	 * 
	 * @param id
	 * 	an int representing the Incident id
	 * @return
	 * 	the deleted Incident
	 * @throws Exception
	 * 	When there are no Incidents left in the ArrayList or none have an ID that matches the given one
	 * @pre
	 * 	deleteIncident() will remove the first Incident, within the ArrayIncident incidentsList, that has an 
	 * 	incidentId that matches the one given. After it removed the Incident it will return the removed Incident. 
	 * @post
	 * 	The result of deleteIncident() will be an arrayList with one less incident. 
	 */
	public Incident deleteIncident(int id) 
			throws Exception {
		update();
		if (index >= 0 && this.incidentsList.size() > index){
			Incident temp;
			for(int i = 0; i < this.incidentsList.size(); i++) {
				temp = this.incidentsList.get(i);
				if (id == temp.getIncidentId()) {
					this.incidentsList.remove(i);
					return temp;
				} // if (id == temp.getIncidentId())
			} //for
		} // if
		else{
			this.index = 0;
			throw new Exception("No Incidents left in ArrayList");
		} // else
		throw new Exception("No Incidents with an ID of " + id);
	} // deleteIncident(int)
	
	/**
	 * 
	 * @param title
	 * 	a String representing the Incident title
	 * @return
	 * 	the deleted Incident
	 * @throws Exception
	 * 	When there are no Incidents left in the ArrayList or none have a title that matches the one given.
	 * @pre
	 * 	deleteIncident() will remove the first Incident, within the ArrayIncident incidentsList, that has an 
	 * 	incidentId that matches the one given. After it removed the Incident it will return the removed Incident. 
	 * @post
	 * 	The result of deleteIncident() will be an arrayList with one less incident. 
	 */
	public Incident deleteIncident(String title) 
			throws Exception {
		update();
		if (index >= 0 && this.incidentsList.size() > index){
			Incident temp;
			for(int i = 0; i < this.incidentsList.size(); i++) { 
				temp = this.incidentsList.get(i);
				if (0 == title.compareTo(temp.getIncidentTitle())) {
					this.incidentsList.remove(i);
					return temp;
				} // if (id == temp.getIncidentId())
			} //for
		} // if
		else{
			this.index = 0;
			throw new Exception("No Incidents left in ArrayList");
		} // else
		throw new Exception("No Incident with an Title of " + title);
	} // deleteIncident(String)
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/*////////////////////////////////
	 * Methods to be added:			//
	 * getPending()					//
	 * getNextPending()				//
	 * approve()					//
	 *//////////////////////////////*/	
	
	/**
	 * 
	 * @param index
	 * 	an integer that marks which Incident the client will consider for approval.
	 * @pre
	 * 	approve() will take an integer and will get the incident from the array list that has that integer
	 * 	as its index. After obtaining the correct incident approve() will then ask the client if they want
	 * 	to approve the current incident. If the client says yes the Incident will be approved, if the user
	 * 	says no the Incident will not be approved. 
	 * @post
	 */
	public void approve(int index) {
		
	} // approve(int)

	
	/**
	 * 
	 * @return
	 * 	An arrayList of pending Incidents objects
	 * @pre
	 * 	getPending will only get the pending incidents from the URL given to the constructor.
	 * @post
	 * 	The result of getPending() will be an arrayList of all pending incidents from the URL given
	 * 	to the constructor.
	 */
	public ArrayList<Incident> getPending() {
		ArrayList<Incident> pendingIncidents = new ArrayList<Incident>();
		
		return pendingIncidents;
	} // getPending()
	
	
	public ArrayList<Incident> getIncidentsList() {
		return this.incidentsList;
	}// getIncidentsList()
} // Ushahidi   

