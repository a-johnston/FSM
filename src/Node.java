import java.util.HashMap;

public class Node {
		public String name;
		private HashMap<String,String> links;
		public Node(String name) {
			this.name  = name;
			this.links = new HashMap<String,String>();
		}
		public void addLink(String digest, String to) {
			links.put(digest, to);
		}
		public String getLink(String digest) {
			return links.get(digest);
		}
		@Override
		public int hashCode() {
			return name.hashCode();
		}
	}