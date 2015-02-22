import java.util.HashMap;
import java.util.LinkedList;

public class Node {
	public String  name;
	public boolean passing;
	private FSM parent;
	private HashMap<String,LinkedList<String>> links;
	public Node(FSM fsm, String name) {
		this.parent = fsm;
		this.name   = name;
		this.links  = new HashMap<String,LinkedList<String>>();
	}
	public void setPassing(boolean passing) {
		this.passing = passing;
	}
	public boolean getPassing() {
		return passing;
	}
	public void addLink(String digest, String... to) {
		LinkedList<String> ll = links.get(digest);
		if (ll == null) {
			ll = new LinkedList<String>();
			links.put(digest, ll);			
		}
		for (String s : to) {
			System.out.println(name+" : "+digest+" : "+s);
			ll.add(s);
		}
	}
	private LinkedList<String> getLinks(String digest) {
		return links.get(digest);
	}
	public boolean pass(String digest) {
		if (digest.equals("")) {
			return passing;
		}
		String str = "";
		int i = 0;
		while (i <= digest.length()) {
			LinkedList<String> tlist = getLinks(str);
			if (tlist != null) {
				for (String temp : tlist) {
					Node n = parent.getNode(temp);
					if ((n != null) && (n.pass(digest.substring(i)))) {
						return true;
					}
				}
			}
			str += digest.charAt(i++);
			
		}
		return false;
	}
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}