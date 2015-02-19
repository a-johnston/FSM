import java.util.HashMap;
public class FSM {
	private HashMap<String,Node> nodes;
	private String init;
	public FSM() {
		nodes = new HashMap<String,Node>();
		init  = null;
	}
	public void addNode(Node n) {
		nodes.put(n.name, n);
	}
	public boolean pass(String s) {
		Node current = getNode(init);
		if (current == null) {
			System.out.println("Cannot find init node. Quitting..");
			return false;
		}
		String digest = "";
		int i=0;
		while (i != s.length()) {
			digest += String.valueOf(s.charAt(i++));
			String temp = current.getLink(digest);
			if (temp != null) {
				current = nodes.get(temp);
				if (current == null) {
					System.out.println("Cannot find returned node: "+temp);
					return false;
				}
				digest = "";
			}
		}
		return false;
	}
	private Node getNode(String name) {
		return nodes.get(name);
	}
}
