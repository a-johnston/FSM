public class Reader {
	public static void main(String[] args) {
//		Node n1 = new Node("THIS");
//		Node n2 = new Node("THAT");
//		n1.addLink("a", "THAT");
//		n1.setPassing(false);
//		n2.setPassing(true);
//		n2.addLink("bbb", "THIS");
//		FSM fsm = new FSM();
//		fsm.addNode(n1);
//		fsm.addNode(n2);
//		fsm.setInit("THIS");
//		System.out.println(fsm.pass("abbba"));
		
		//parse("[abs]{hello");
	}
	public static FSM parse(String data) {
		FSM fsm = new FSM();
		int i = 0, open, end;
		while (i < data.length()) {
			open = data.indexOf('[', i)+1;
			end  = data.indexOf(']', open);
			if (open == 0) {
				System.out.println("ERROR: failed to parse state class");
				return fsm;
			}
			if (end == -1) {
				System.out.println("ERROR: failed to find end of state class");
				return fsm;
			}
			Node n = new Node(data.substring(open, end));
			fsm.addNode(n);
			open = data.indexOf('{', end+1)+1;
			end  = data.indexOf('}', open);
			if (open == 0) {
				System.out.println("ERROR: failed to parse state body : " + n.name);
				return fsm;
			}
			if (end == -1) {
				System.out.println("ERROR: failed to find end of state body : " + n.name);
				return fsm;
			}
			System.out.println(open + " " + data.substring(open, end) + " " + end);
			
			i = end+1;
		}
		return fsm;
	}
	private static boolean buildStateLinks(Node n, String body) {
		body = body.replaceAll("( |\t)", "");
		System.out.println(body);
		return true;
	}
}
