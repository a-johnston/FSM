import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
	private static boolean skipErrors = false;
	public static void main(String[] args) {
		FSM fsm = parse(readFile("sample.fsm"));
		System.out.println(fsm.pass("abba"));
	}
	public static FSM parse(String data) {
		FSM fsm = new FSM();
		int i = 0, open, end;
		while (i < data.length()) {
			open = data.indexOf('[', i)+1;
			end  = data.indexOf(']', open);
			if (open == 0) {
				return fsm;
			}
			if (end == -1) {
				System.out.println(getErrorString("failed to find end of state class"));
				return fsm;
			}
			Node n = new Node(fsm, data.substring(open, end));
			if (data.charAt(end+1) == '*') {
				n.setPassing(true);
			}
			fsm.addNode(n);
			open = data.indexOf('{', end+1)+1;
			end  = data.indexOf('}', open);
			if (open == 0) {
				System.out.println(getErrorString("failed to parse state body : " + n.name));
				return fsm;
			}
			if (end == -1) {
				System.out.println(getErrorString("failed to find end of state body : " + n.name));
				return fsm;
			}
			parseStateLinks(n, data.substring(open, end));
			i = end+1;
		}
		return fsm;
	}
	private static boolean parseStateLinks(Node n, String body) {
		body = body.replaceAll("( |\t)", "");
		for (String d : body.split("\n")) {
			if (!parseLinkData(n, d)) {
				System.out.println(getErrorString("failed to parse " + d + " in " + n.name));
				if (!skipErrors) {
					return false;
				}
			}
		}
		return true;
	}
	private static boolean parseLinkData(Node n, String data) {
		if (data.equals("")) {
			return true;
		}
		String[] parts = data.split("(>|-->|->)");
		if (parts.length != 2) {
			System.out.println(getErrorString("incorrect syntax in "+data));
			return false;
		}
		n.addLink(parts[0], parts[1].split("[\\|]"));
		return true;
	}
	private static String getErrorString(String message) {
		return (skipErrors?"WARNING: ":"ERROR: ")+message;
	}
	private static String readFile(String filename) {
		String data = "";
		try {
			File file = new File(filename);
			Scanner input = new Scanner(file);
			data = input.useDelimiter("\\A").next();
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: "+filename+" not found");
		}
		return data;
	}
}
