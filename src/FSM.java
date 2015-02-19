import java.util.HashMap;
public class FSM {
	/**
	 * Gives a status of an FSM evaluation
	 *
	 */
	public static enum Status {
		PASS,
		FAIL,
		NONHALTING,
		NOTINITIALIZED
	};
	private HashMap<String,Node> nodes;
	private String init;
	public FSM() {
		nodes = new HashMap<String,Node>();
		init  = null;
	}
	public void setInit(String n) {
		init = n;
	}
	public void addNode(Node n) {
		nodes.put(n.name, n);
	}
	public FSM.Status pass(String s) {
		Node current = getNode(init);
		if (current == null) {
			System.out.println("Cannot find init node. Quitting..");
			return Status.NOTINITIALIZED;
		}
		System.out.println("Starting traversal at " + init);
		String digest = "";
		int i=0;
		while (i != s.length()) {
			digest += String.valueOf(s.charAt(i++));
			String temp = current.getLink(digest);
			if (temp != null) {
				current = nodes.get(temp);
				if (current == null) {
					System.out.println("Cannot find returned node: "+temp);
					return Status.NOTINITIALIZED;
				}
				System.out.println("At " + digest + ", moved to "+current.name);
				digest = "";
			}
		}
		if (digest == "") {
			if (current.getPassing()) {
				return Status.PASS;
			}
			return Status.FAIL;
		}
		return Status.NONHALTING;
	}
	private Node getNode(String name) {
		return nodes.get(name);
	}
}
