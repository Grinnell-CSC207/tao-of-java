package ushahidi;

public class Test {
	
	/*Other urls that can be used:
	 * https://farmersmarket.crowdmap.com
	 * https://restorethe4th.crowdmap.com
	 * http://www.greatlakescommonsmap.org
	 * https://womenundersiegesyria.crowdmap.com
	 * http://132.161.197.61/ushahidi*/
	
	public static void main (String [] args) 
		throws Exception 
	{	
	
	API test = new API("http://www.greatlakescommonsmap.org");
	//Print out all Incidents in the Array List after the constructor is ran.
	//for (int i = 0; i< test.incidentsList.size(); i++) 
		System.out.println(test.nextIncident().toString());
	//Moving through the incidentsList ArrayList
	//Moving forward in the ArrayList
	System.out.println("Moving foward in the array list:");
	for(int i = 0; i < 3; i++) 
		System.out.println("-" + test.nextIncident().getIncidentTitle());
	
	//Moving backward in the Arraylist
	System.out.println("\nMoving backward in the array list:");
	for(int i = 0; i < 3; i++)
		System.out.println("-" + test.prevIncident().getIncidentTitle());
	
	//Test delete methods.
	//delete first incident and show which incident is deleted
		System.out.print("\nDeleting first Incident in the array list:\n\n" + test.deleteIncident().toString() + "\n");
	//Is the incident still at the same index?
		System.out.println("\nInicdent that is now at the front of the list:\n\n" + test.getIncidentsList().get(0) + "\n");
	} // main(String)
} // Test




