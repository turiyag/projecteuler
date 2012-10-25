import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Inspector {
	private int						_iTabLevel	= 0;
	private Object					_oInput;
	private Class<?>				_cInput;
	private LinkedHashSet<Class<?>>	_clInheritanceHeirarchy;
	private boolean					_bRecursive;
	
	public void inspect(Object obj, boolean recursive) {
		if (obj == null) {
			System.out.println("Object is null");
			return;
		}
		
		_bRecursive = recursive;
		_oInput = obj;
		_cInput = obj.getClass();
		_clInheritanceHeirarchy = getInheritanceHeirarchy();
		
		forTheRecordLn("Name: " + getNiceName(_cInput));
		tabIn();
		forTheRecordLn("Immediate Superclass: " + getNiceName(_cInput.getSuperclass()));
		printInterfaces();
		printConstructors();
		printMethods();
		printFields();
		tabOut();
	}
	
	private LinkedHashSet<Class<?>> getInheritanceHeirarchy() {
		LinkedHashSet<Class<?>> clCHL = new LinkedHashSet<Class<?>>();
		if (_cInput != null) {
			clCHL.addAll(superInterfaceHeirarchyList(_cInput));
			Class<?> cSuper = _cInput.getSuperclass();
			while (cSuper != null) {
				clCHL.add(cSuper);
				clCHL.addAll(superInterfaceHeirarchyList(cSuper));
				cSuper = cSuper.getSuperclass();
			}
		}
		return clCHL;
	}
	
	private LinkedHashSet<Class<?>> superInterfaceHeirarchyList(Class<?> c) {
		LinkedHashSet<Class<?>> clIHL = new LinkedHashSet<Class<?>>();
		for (Class<?> cInterface : c.getInterfaces()) {
			clIHL.add(cInterface);
			clIHL.addAll(superInterfaceHeirarchyList(cInterface));
		}
		return clIHL;
	}
	
	private void printInterfaces() {
		forTheRecordLn("Interfaces:");
		tabIn();
		for (Class<?> cInterface : _cInput.getInterfaces()) {
			forTheRecordLn(getNiceName(cInterface));
		}
		for (Class<?> cSuper : _clInheritanceHeirarchy) {
			for (Class<?> cInterface : cSuper.getInterfaces()) {
				forTheRecordLn("[From " + getNiceName(cSuper) + "] " + getNiceName(cInterface));
			}
		}
		tabOut();
	}
	
	private void printConstructors() {
		forTheRecordLn("Constructors:");
		tabIn();
		for (Constructor<?> cConstr : _cInput.getConstructors()) {
			forTheRecordLn(Modifier.toString(cConstr.getModifiers()) + " " + getNiceName(_cInput) + "(" + constructorParameters(cConstr) + ")");
		}
		for (Class<?> c : _clInheritanceHeirarchy) {
			for (Constructor<?> cConstr : c.getConstructors()) {
				forTheRecordLn(Modifier.toString(cConstr.getModifiers()) + " " + getNiceName(c) + "(" + constructorParameters(cConstr) + ")");
			}
		}
		tabOut();
	}
	
	private void printMethods() {
		forTheRecordLn("Methods:");
		tabIn();
		for (Method m : _cInput.getDeclaredMethods()) {
			forTheRecordLn(Modifier.toString(m.getModifiers()) + " " + getNiceName(m.getReturnType()) + " " + m.getName() + "(" + methodParameters(m) + ") " + methodExceptions(m));
		}
		for (Class<?> c : _clInheritanceHeirarchy) {
			for (Method m : c.getDeclaredMethods()) {
				forTheRecordLn("[From " + getNiceName(c) + "] " + Modifier.toString(m.getModifiers()) + " " + getNiceName(m.getReturnType()) + " " + m.getName() + "(" + methodParameters(m) + ") " + methodExceptions(m));
			}
		}
		tabOut();
	}
	
	private void printFields() {
		Object oFieldValue;
		forTheRecordLn("Fields:");
		tabIn();
		for (Field f : _cInput.getDeclaredFields()) {
			try {
				f.setAccessible(true);
				oFieldValue = f.get(_oInput);
				if (oFieldValue == null) {
					forTheRecordLn(Modifier.toString(f.getModifiers()) + " " + getNiceName(f.getType()) + " " + f.getName() + " = null");
				} else {
					forTheRecord(Modifier.toString(f.getModifiers()) + " " + getNiceName(f.getType()) + " " + f.getName() + " = ");
					possibleArrayToString(oFieldValue);
					System.out.println();
				}
			} catch (IllegalArgumentException e) {
				forTheRecordLn(Modifier.toString(f.getModifiers()) + " " + getNiceName(f.getType()) + " " + f.getName() + " = *IllegalArgument*");
			} catch (IllegalAccessException e) {
				forTheRecordLn(Modifier.toString(f.getModifiers()) + " " + getNiceName(f.getType()) + " " + f.getName() + " = *IllegalAccess*");
			}
		}
		for (Class<?> c : _clInheritanceHeirarchy) {
			printFields(c);
		}
		tabOut();
	}
	
	private void printFields(Class<?> cObject) {
		Object oFieldValue;
		for (Field f : cObject.getDeclaredFields()) {
			try {
				f.setAccessible(true);
				oFieldValue = f.get(_oInput);
				if (oFieldValue == null) {
					forTheRecordLn("[From " + getNiceName(cObject) + "] " + Modifier.toString(f.getModifiers()) + " " + getNiceName(f.getType()) + " " + f.getName() + " = null");
				} else {
					forTheRecord("[From " + getNiceName(cObject) + "] " + Modifier.toString(f.getModifiers()) + " " + getNiceName(f.getType()) + " " + f.getName() + " = ");
					possibleArrayToString(oFieldValue, f.getType().isPrimitive());
					System.out.println();
				}
			} catch (IllegalArgumentException e) {
				forTheRecordLn("[From " + getNiceName(cObject) + "] " + Modifier.toString(f.getModifiers()) + " " + getNiceName(f.getType()) + " " + f.getName() + " = *IllegalArgument*");
			} catch (IllegalAccessException e) {
				forTheRecordLn("[From " + getNiceName(cObject) + "] " + Modifier.toString(f.getModifiers()) + " " + getNiceName(f.getType()) + " " + f.getName() + " = *IllegalAccess*");
			}
		}
	}
	
	private void possibleArrayToString(Object oInput, boolean bPrimitive) {
		int iLength;
		StringBuffer sb = new StringBuffer();
		String sReturn;
		Object[] oaInput;
		if (oInput == null) {
			return;
		}
		if (oInput.getClass().isArray()) {
			if (oInput instanceof Object[]) {
				System.out.print("[");
				oaInput = (Object[]) oInput;
				for (int i = 0; i < oaInput.length; i++) {
					possibleArrayToString(oaInput[i]);
					if (i != oaInput.length - 1) {
						System.out.print(", ");
					}
				}
				System.out.print("]");
				return;
			} else {
				iLength = Array.getLength(oInput);
				Object[] objArr = new Object[iLength];
				for (int i = 0; i < iLength; i++) {
					objArr[i] = Array.get(oInput, i);
				}
				System.out.print(Arrays.deepToString(objArr));
				return;
			}
		} else {
			if (_bRecursive) {
				if (oInput.getClass().()) {
					System.out.print(oInput);
					return;
				} else {
					Inspector i = new Inspector();
					i.inspect(oInput, _bRecursive);
					return;
				}
			} else {
				System.out.print(oInput);
				return;
			}
		}
	}
	
	private String getNiceName(Class<?> c) {
		if (c.isArray()) {
			return getNiceName(c.getComponentType()) + "[]";
		} else {
			if (c.isInterface()) {
				return "Interface " + c.getName();
			} else {
				return c.getName();
			}
		}
	}
	
	private String constructorParameters(Constructor<?> c) {
		StringBuffer sb = new StringBuffer();
		String sResult;
		for (Class<?> cParam : c.getParameterTypes()) {
			sb.append(getNiceName(cParam) + ", ");
		}
		sResult = sb.toString();
		if (sResult.isEmpty()) {
			return sResult;
		} else {
			return sResult.substring(0, sResult.length() - 2);
		}
	}
	
	private String methodExceptions(Method m) {
		StringBuffer sb = new StringBuffer();
		String sResult;
		for (Class<?> cException : m.getExceptionTypes()) {
			sb.append(getNiceName(cException) + ", ");
		}
		sResult = sb.toString();
		if (sResult.isEmpty()) {
			return sResult;
		} else {
			return "throws " + sResult.substring(0, sResult.length() - 2);
		}
	}
	
	private String methodParameters(Method m) {
		StringBuffer sb = new StringBuffer();
		String sResult;
		for (Class<?> cParam : m.getParameterTypes()) {
			sb.append(getNiceName(cParam) + ", ");
		}
		sResult = sb.toString();
		if (sResult.isEmpty()) {
			return sResult;
		} else {
			return sResult.substring(0, sResult.length() - 2);
		}
	}
	
	public void forTheRecordLn(String s) {
		for (int i = 0; i < _iTabLevel; i++) {
			System.out.print("\t");
		}
		System.out.println(s);
	}
	
	public void forTheRecord(String s) {
		for (int i = 0; i < _iTabLevel; i++) {
			System.out.print("\t");
		}
		System.out.print(s);
	}
	
	public void tabIn() {
		_iTabLevel++;
	}
	
	public void tabOut() {
		_iTabLevel--;
	}
}
