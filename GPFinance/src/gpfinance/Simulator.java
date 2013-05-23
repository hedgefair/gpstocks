package gpfinance;

import gpfinance.algorithm.*;
import gpfinance.datatypes.*;
import gpfinance.tree.*;

/**
 * @date 2013-06-01
 * @author Simon van Dyk, Stuart Reid
 */
public class Simulator extends Thread {
    
    private String[] args = null;

    public Simulator(String[] args) { this.args = args; }

    @Override
    public void run() {
        // Parse
        // TODO parse this.args and create appropriate GP() and run it
        
        // Dispatch
        GP algorithm = new GP();
        algorithm.run();
    }
    
    public void test() {
        Test test = new Test(args);
    }

    private class Test {
        public Test(String[] args) {
            // Parse args
            String c = "";
            for (String s : args) {
                c += s + " ";
            }

            // Dispatch
            if (c.contains("all")) {
                testDataTypes();
                testTree();
                testRandomGenerators();
                initialization();
                selection();
                mutation();
                crossover();
            }
            if (c.contains("datatypes")) {
                testDataTypes();
            }
            if (c.contains("tree")) {
                testTree();
            }
            if (c.contains("random")) {
                testRandomGenerators();
            }
            if (c.contains("all-operators")){
                initialization();
                selection();
                mutation();
                crossover();
            }
            if (c.contains("initialization")){
                initialization();
            }
            if (c.contains("selection")){
                selection();
            }
            if (c.contains("mutation")){
                mutation();
            }
            if (c.contains("crossover")){
                crossover();
            }
            if (c.contains("adhoc")){
                adhoc();
            }
        }

        private void testDataTypes() {
            U.m("\n*** Testing data types");
            Decision[] decisions = {Decision.BUY, Decision.SELL};
            for (Decision d : decisions) {
                U.m(d);
            }

            Indicator[] techInd = {Tech.EXAMPLE};
            Indicator[] fundInd = {Fund.RAD};
            for (int i = 0; i < 5; ++i) {
                U.m(Fund.getRandom());
            }

            for (Indicator d : techInd) {
                U.m(d);
            }
            for (Indicator d : fundInd) {
                U.m(d);
            }
            
            // data type cloning (deep copies)
        }

        private void testTree() {
            // init tree
            U.m("\n*** Testing trees");
            DecisionTree tree = new DecisionTree('F', 5);
            // size
            U.m("Size: " + tree.size());
            U.m("Avg depth: " + tree.avgDepth());
            // print
            U.m("Print tree");
            tree.print();

            U.m("Finding random positions in tree...");
            Node[] nodes;
            for (int i = 0; i < 3; ++i){
                nodes = tree.getRandomNonterminalNode(false);
                U.m(i + " - FINAL FOUND: prev=\"" + nodes[0] + "\", node=\"" + nodes[1] + "\"");
            }
            
            // trunc
            
        }
        
        private void testSelection(){
            //random
            //mu+lambda
            //rank based
        }
        
        private void testMutation(){
            // 6 types
        }
        
        private void testCrossover(){
            // destructive sexual crossover
        }

        private void testRandomGenerators() {
            U.m("\n*** Testing random");
            U.m("...test randomVal()");
            for (int i = 0; i < 10; ++i) {
                U.m(U.randomVal());
            }
            U.m("...test gauss random");
            for (int i = 0; i < 10; ++i) {
                U.m(U.getRandomGauss((double)i));
            }
        }

        private void initialization() {
            char type = 'F';
            int numindividuals = 5;
            Individual[] ins = new Individual[numindividuals];
            for (int i = 0; i < numindividuals; ++i){
                ins[i] = new Individual(type);
            }
            for (int i = 0; i < numindividuals; ++i){
                U.m("Tree #" + i);
                ins[i].print();
            }
        }

        private void selection() {
        }

        private void mutation() {
            Individual in = new Individual('F');
            int mutations = 10;
            
            //grow
            U.m("Before grow:");
            in.print();
            for (int i = 0; i < mutations; ++i){
                in.mutateGrow();
            }
            U.m("After grow:");
            in.print();
            
            
            //trunc
           /*
            * as seen below (performing half of the trunc operations as mutations)
            * since trunc chooses a random non terminal and replaces it was a terminal
            * node, effectively chopping off an entire subtree... I think we should
            * maybe take depth into account here, otherwise with large trees it may
            * become too destructive, simply chopping potentially good branches.
            */
            
            
            U.m("Before trunc:");
            in.print();
            for (int i = 0; i < mutations/2; ++i){
                in.mutateTrunc();
            }
            U.m("After trunc:");
            in.print();
            
            
            //swap inequality
            U.m("Before swap inequality:");
            in.print();
            for (int i = 0; i < mutations; ++i){
                in.mutateSwapInequality();
            }
            U.m("After swap inequality:");
            in.print();
            
            //non-terminal (indicator)
            U.m("Before indicator mutate:");
            in.print();
            for (int i = 0; i < mutations; ++i){
                in.mutateNonLeaf();
            }
            U.m("After indicator mutate:");
            in.print();
            
            //terminal (swap decisionnode)
            U.m("Before leaf:");
            in.print();
            for (int i = 0; i < mutations; ++i){
                in.mutateLeaf();
            }
            U.m("After leaf:");
            in.print();
            
            //gauss
            U.m("Before gauss:");
            in.print();
            for (int i = 0; i < mutations; ++i){
                in.mutateGauss();
            }
            U.m("After gauss:");
            in.print();
            /**/
            
        }

        private void crossover() {
        }
        
        private void adhoc(){
//            DecisionTree tree = new DecisionTree('F');
//            
//            U.m("Before grow:");
//            tree.print();
//            for (int i = 0; i < 15; ++i){
//                tree.insertRandom();
//            }
//            U.m("After grow, Before trunc:");
//            tree.print();
//            for (int i = 0; i < 10; ++i){
//                tree.removeRandomLimitedDepth();
//                tree.print();
//            }
//            U.m("After trunc:");
//            tree.print();
            
        }
    }
}
