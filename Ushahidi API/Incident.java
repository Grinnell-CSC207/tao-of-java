/*Required fields found at: https://wiki.ushahidi.com/display/WIKI/Ushahidi+Public+API*/

package ushahidi;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Incident {
	//Required fields.
	private int incidentId = -1; //The identification number corresponding to the incident.
	private String incidentTitle = ""; //Title of the incident.
	private String incidentDescription = ""; //Description of the report.
	private String incidentDate = ""; // The date format is mm/dd/yy.
	private String incidentMode = ""; //The mode the incident is in.
	private String incidentActive = ""; //Is the incident active.
	private String incidentVerified = ""; //Is the incident verified.
	private int locationId = -1; //The identification number corresponding to the location of the incident.
	private String locationName = ""; //The name of the location.
	private double locationLatitude; // Latitude of the report's location.
	private double locationLongitude; //Longitude of the report's location.
	private JSONArray categories = null;//The category id (or list of category i.ds) under which the report should be filed.
	private JSONArray media = null; //A collection of media.
	private JSONArray comments = null; //Additional comments on the incident.
	private JSONArray error = null; //Is there an error involving this incident.
	private JSONArray customFields = null; //Any additional fields the client wishes to add.
	
	
	private double invalidLatitude = 191919;
	private double invalidLongitude = 191919;
	public Incident() {
		
	} // Incident()
	
	public Incident(int id, String title) {
		this.incidentId = id;
		this.incidentTitle = title;
	} // Incident(int, String)
	
	public Incident(JSONObject input) {
		incidentId = Integer.parseInt(input.getJSONObject("incident").get("incidentid").toString());
		incidentTitle = input.getJSONObject("incident").get("incidenttitle").toString();
		incidentDescription = input.getJSONObject("incident").get("incidentdescription").toString();
		incidentDate = input.getJSONObject("incident").get("incidentdate").toString();
		incidentMode = input.getJSONObject("incident").get("incidentmode").toString();
		incidentActive = input.getJSONObject("incident").get("incidentactive").toString();
		incidentVerified = input.getJSONObject("incident").get("incidentverified").toString();
		locationId = Integer.parseInt(input.getJSONObject("incident").get("locationid").toString());
		locationName = input.getJSONObject("incident").get("locationname").toString();
		
		//COmpensate for null fields on latitude longitude location id
		locationLatitude = Double.parseDouble(input.getJSONObject("incident").get("locationlatitude").toString());
		locationLongitude = Double.parseDouble(input.getJSONObject("incident").get("locationlongitude").toString());
		categories = input.getJSONArray("categories");
		media = input.getJSONArray("media");
		comments = input.getJSONArray("comments");
		
		try {error = input.getJSONArray("error");
		} // try{error = input.getJSONArray("error")}
		catch(JSONException e) {
			error = null;
		} // catch(JSONException e)
		
		try {customFields = input.getJSONArray("customfields");
		} // try{customFields = input.getJSONArray("customfields")}
		catch(JSONException e){
			customFields = null;
		} // catch(JSONException e)
	} // Incident(JSONObject)

	public String toString() {
		return "INCIDENT ["+ "Title: " + this.incidentTitle + "\nID: " + this.incidentId +"\nDescription: " + this.incidentDescription + "\nDate: " + this.incidentDate 
				+ "\nLocation ID: " + this.locationId + "\nLocation Name: " + this.locationName + "\nLocation Latitude: " + this.locationLatitude 
				+ "\nLocation Longitude: " + this.locationLongitude + "]\n";
	} // toString()

	//Get methods for the fields within the Incident class
	public int getIncidentId() throws Exception{
		if (incidentId == -1)
			throw new Exception("IncidentID is unknown");
		return incidentId;
	} // getIncidentId

	public String getIncidentTitle() throws Exception{
		if (incidentTitle == "")
			throw new Exception("Incident Title is unknown");
		return incidentTitle;
	} // getIncidentTitle

	public String getIncidentDescription() throws Exception{
		if (incidentDescription == "")
			throw new Exception("Incident description is unknown");
		return incidentDescription;
	} // getIncidentDescription

	public String getIncidentDate() throws Exception{
		if (incidentDate == "")
			throw new Exception("Incident date is unknown");
		return incidentDate;
	} // getIncidentDate

	public String getIncidentMode() throws Exception{
		if (incidentMode == "")
			throw new Exception("IncidentMode is unknown");
		return incidentMode;
	} // getIncidentMode()
	
	public String getIncidentActive() throws Exception{
		if (incidentActive == "")
			throw new Exception("Incident Activity is unknown");
		return incidentActive;
	} // getIncidentActive()

	public String getIncidentVerified() throws Exception{
		if (incidentVerified == "")
			throw new Exception("Verification is unknown");
		return incidentVerified;
	} // getIncidentVerified()

	public int getLocationId() throws Exception{
		if (locationId == -1)
			throw new Exception("Location ID is unknown");
		return locationId;
	} // getLocationId()

	public String getLocationName() throws Exception{
		if (locationName == "")
			throw new Exception("Location name is unknown");
		return locationName;
	} // getLocationName()

	public double getLocationLatitude() throws Exception{
		return locationLatitude;
	} // getLocationLatitude()

	public double getLocationLongitude() throws Exception{
		return locationLongitude;
	} // getLocationLongitude()

	public JSONArray getCategories() {
		return categories;
	} // getCategories()

	public JSONArray getError() {
		return error;
	} // getError()
	
	public JSONArray getComments() {
		return comments;
	} // getComments()
	
	public JSONArray getMedia() {
		return media;
	} // getMedia()

	public JSONArray getCustomFields() {
		return customFields;
	} // getCustomFields()
} // Incident