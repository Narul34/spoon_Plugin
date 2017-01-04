package org.test.pentaho;

import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.pentaho.ui.xul.XulException;
import org.pentaho.ui.xul.binding.Binding;
import org.pentaho.ui.xul.binding.BindingContext;
import org.pentaho.ui.xul.binding.BindingConvertor;
import org.pentaho.ui.xul.binding.BindingConvertor.Direction;
import org.pentaho.ui.xul.binding.BindingExceptionHandler;

public class MyBinding implements Binding {

	@Override
	public void bindForward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindReverse() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroyBindings() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object doConversions(Object arg0, Direction arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object evaluateExpressions(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fireSourceChanged() throws IllegalArgumentException,
			XulException, InvocationTargetException {
		// TODO Auto-generated method stub

	}

	@Override
	public Type getBindingType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BindingContext getContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BindingConvertor getConversion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyChangeListener getForwardListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyChangeListener> getListeneners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyChangeListener getReverseListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSourceAttr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTargetAttr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isReverseConversion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBindingType(Type arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContext(BindingContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setConversion(BindingConvertor arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setExceptionHandler(BindingExceptionHandler arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setForwardListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setReverseConversion(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setReverseListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSource(Object arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSourceAttr(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTarget(Object arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTargetAttr(String arg0) {
		// TODO Auto-generated method stub

	}

}
