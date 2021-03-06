package com.douzone.mysite.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("write".equals(actionName)) {
			action = new WriteAction();
		} else if("writeattach".equals(actionName)) {
			action = new WriteAttachAction();
		} else if("writeattachform".equals(actionName)) {
			action = new WriteAttachFormAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("updateform".equals(actionName)) {
			action = new UpdateFormAction();
		} else if("update".equals(actionName)) {
			action = new UpdateAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		} else if("page".equals(actionName)) {
			action = new PageAction();
		} else {
			
		}
		return action;
	}

}
