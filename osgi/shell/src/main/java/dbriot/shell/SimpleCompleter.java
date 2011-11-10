package dbriot.shell;

import java.util.List;

import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

public class SimpleCompleter implements Completer {

	@Override
	public int complete(String buffer, int cursor, List<String> candidates) {
		StringsCompleter delegate = new StringsCompleter();
		delegate.getStrings().add("one");
		delegate.getStrings().add("two");
		delegate.getStrings().add("three");
		return delegate.complete(buffer, cursor, candidates);
	}

}
