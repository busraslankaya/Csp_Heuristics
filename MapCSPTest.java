package aima.test.core.unit.search.csp;

import aima.core.search.csp.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import aima.core.search.csp.examples.MapCSP;

import java.util.Optional;

/**
 * @author Ravi Mohan
 * @author Ruediger Lunde
 * 
 */
public class MapCSPTest { 
	private CSP<Variable, String> csp;

	@Before
	public void setUp() {
		csp = new MapCSP();
	}

	CspListener.StepCounter<Variable, String> stepCounter = new CspListener.StepCounter<>();
	CspSolver<Variable, String> solver;
	Optional<Assignment<Variable, String>> solution;
	@Test
	public void testBackTrackingSearch() {
		Optional<Assignment<Variable, String>> results = new FlexibleBacktrackingSolver<Variable, String>().solve(csp);
		Assert.assertTrue(results.isPresent());
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.WA));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.NT));
		Assert.assertEquals(MapCSP.BLUE, results.get().getValue(MapCSP.SA));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.Q));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.NSW));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.V));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.T));
		
		solver = new FlexibleBacktrackingSolver<>();
		solver.addCspListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Backtracking)");
		solution = solver.solve(csp);
		solution.ifPresent(System.out::println);
		System.out.println(stepCounter.getResults() + "\n");
	
	}
	
	//start with region where colour option is less
	@Test
	public void testMrvDegSearch() {
		Optional<Assignment<Variable, String>> results = new FlexibleBacktrackingSolver<Variable, String>().set(CspHeuristics.mrvDeg()).solve(csp);
		Assert.assertTrue(results.isPresent());
		Assert.assertEquals(MapCSP.BLUE, results.get().getValue(MapCSP.WA));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.NT));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.SA));
		Assert.assertEquals(MapCSP.BLUE, results.get().getValue(MapCSP.Q));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.NSW));
		Assert.assertEquals(MapCSP.BLUE, results.get().getValue(MapCSP.V));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.T));
		
		
		
		solver = new FlexibleBacktrackingSolver<Variable, String>().set(CspHeuristics.mrvDeg());
		solver.addCspListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Backtracking + MRV & DEG)");
		solution = solver.solve(csp);
		solution.ifPresent(System.out::println);
		System.out.println(stepCounter.getResults() + "\n");
	}
	
	@Test
	public void testLCVSearch() {
		Optional<Assignment<Variable, String>> results = new FlexibleBacktrackingSolver<Variable, String>().set(CspHeuristics.lcv()).solve(csp);
		Assert.assertTrue(results.isPresent());
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.WA));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.NT));
		Assert.assertEquals(MapCSP.BLUE, results.get().getValue(MapCSP.SA));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.Q));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.NSW));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.V));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.T));
		
		
		solver = new FlexibleBacktrackingSolver<Variable, String>().set(CspHeuristics.lcv());
		solver.addCspListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Backtracking + LCV)");
		solution = solver.solve(csp);
		solution.ifPresent(System.out::println);
		System.out.println(stepCounter.getResults() + "\n");
	}
	
	@Test
	public void testMCSearch() {
		new MinConflictsSolver<Variable, String>(100).solve(csp);
	}
}