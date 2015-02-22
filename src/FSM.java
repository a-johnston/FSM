import java.util.HashMap;
import java.util.LinkedList;
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
		if (init == null) {
			init = n.name;
		}
	}
	public Node getNode(String name) {
		return nodes.get(name);
	}
	public boolean pass(String str) {
		return getNode(init).pass(str);
	}
}
