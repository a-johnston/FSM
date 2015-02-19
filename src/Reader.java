public class Reader {
	public static void main(String[] args) {
		Node n1 = new Node("THIS");
		Node n2 = new Node("THAT");
		n1.addLink("a", "THAT");
		n2.addLink("bbb", "THIS");
		FSM fsm = new FSM();
		fsm.addNode(n1);
		fsm.addNode(n2);
		fsm.setInit("THIS");
		fsm.pass("abbba");
	}
	public FSM parse(String data) {
		FSM fsm = new FSM();
		while (data != "") {
			
		}
		return fsm;
	}
}
