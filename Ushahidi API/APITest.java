package ushahidi;

import static org.junit.Assert.*;

import org.junit.Test;

public class APITest {

	
	@Test
	public void testNextIncident() throws Exception {
		API test = new API("https://womenundersiegesyria.crowdmap.com");
		assertEquals(test.nextIncident(), test.getIncidentsList().get(0));
		assertEquals(test.nextIncident(), test.getIncidentsList().get(1));
		assertEquals(test.nextIncident(), test.getIncidentsList().get(2));
	} // testNextIncident()

	@Test
	public void testPrevIncident() throws Exception{
		API test = new API("https://womenundersiegesyria.crowdmap.com");
		//First we need to move the index of the current Incident so that it
		//is not the first Incident.
		test.nextIncident();
		test.nextIncident();
		test.nextIncident();
		
		//Now that we are further in the array list we can begin to test the 
		//prevIncident() method.
		assertEquals(test.prevIncident(), test.getIncidentsList().get(2));
		assertEquals(test.prevIncident(), test.getIncidentsList().get(1));
		assertEquals(test.prevIncident(), test.getIncidentsList().get(0));
	}

	@Test
	public void testDeleteIncident() throws Exception {
		API test = new API("https://womenundersiegesyria.crowdmap.com");
		Incident originalIncident = test.getIncidentsList().get(0);
		//We start by deleting the first Incident within the array list
		//We also show that deleteIncident does remove and return the 
		//correct Incident
		assertEquals(originalIncident, test.deleteIncident());
		//Now we can show that the incident that was originally the first 
		//incident is no longer there
		 assertNotSame(originalIncident, test.nextIncident());
	}

	@Test
	public void testDeleteIncidentInt() throws Exception {
		API test = new API("https://womenundersiegesyria.crowdmap.com");
		//We know that there is an Incident with an index of 45 that 
		//has an id of 187
		Incident temp = test.getIncidentsList().get(45);
		Incident deletedIncident = test.deleteIncident(187);

		assertEquals(temp, deletedIncident);
		
		//We can show that the deleteIncident() method deleted the correct Incident
		assertEquals(temp.getIncidentId(), 187);
		assertEquals(deletedIncident.getIncidentId(), 187);
		
		//We can show that the deleteIncident() method in fact deleted the Incident
		//from the array list
		assertNotSame(deletedIncident, test.getIncidentsList().get(45));
	}

	@Test
	public void testDeleteIncidentString() throws Exception{
		API test = new API("https://womenundersiegesyria.crowdmap.com");
		//We know that there is an Incident with an title, FSA soldier tells Syrian-American of rape at Hama checkpoint
		//with an index of 45 and an id of 187
		Incident temp = test.getIncidentsList().get(45);
		Incident deletedIncident = test.deleteIncident("FSA soldier tells Syrian-American of rape at Hama checkpoint");
		
		assertEquals(temp, deletedIncident);
		
		//We can show that the deleteIncident() method deleted the correct Incident
		assertEquals(temp.getIncidentId(), 187);
		assertEquals(deletedIncident.getIncidentId(), 187);
		
		//We can show that the deleteIncident() method in fact deleted the Incident
		//from the array list
		assertNotSame(deletedIncident, test.getIncidentsList().get(45));
	}

}
