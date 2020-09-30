package org.sonar.custom.objectscript;

import org.sonar.api.Plugin;

/**
 * This class is the entry point for all extensions
 */
public class ObjectscriptCustomRulesPlugin implements Plugin {

	 @Override
	  public void define(Context context) {
	    context.addExtension(MyObjectscriptRules.class);
	  }
}
