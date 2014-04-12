package ch.ethz.inf.dbproject.util.html;

import java.util.*;

public class MessageHelper extends HtmlHelperIface {
	
	
	private List<String> HtmlMessages = new ArrayList<String>();	
	
	public void ErrorMessage(final String Text){
		String message = "<div id=\"errormessage\">" + Text + "</div><br/>";
		HtmlMessages.add(message);
	}
	
	public void SuccessMessage(final String Text){
		String message = "<div id=\"successmessage\">" + Text + "</div><br/>";
		HtmlMessages.add(message);
	}
	
	
	@Override
	public final String generateHtmlCode() {
		String result="";
		for(String message:HtmlMessages){
			result = result + message;
		}
		HtmlMessages.clear();
		return result;
	}
}
