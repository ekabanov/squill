package squill.mgen;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.objectweb.asm.*;

public class ClassInspectionUtil {
	private static final int BUFFER_SIZE = 8194;
	
	public static class MethodInfo {
		private String name;
		private String desc;
		private int access;
		
		public MethodInfo(int access, String name, String desc) {
			this.access = access;
			this.desc = desc;
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public String getDesc() {
			return desc;
		}
		
		public int getAccess() {
			return access;
		}
	}
	
	public static class ClassInfo {
		public String name;
		public Map<String, MethodInfo> methods = new HashMap<String, MethodInfo>();
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public Map<String, MethodInfo> getMethods() {
			return methods;
		}
		
		public void addMethod(MethodInfo methodInfo) {
			methods.put(methodInfo.getName(), methodInfo);
		}
	}
	
	public static class LookupClassVisitor extends ClassAdapter {
		private final ClassInfo classInfo;
		private Map<String, File> modelClassFiles;
		
		public LookupClassVisitor(ClassInfo classInfo, ClassVisitor visitor, Map<String, File> modelClassFiles) {
			super(visitor);
			this.classInfo = classInfo;
			this.modelClassFiles = modelClassFiles;
		}
		
		@Override
		public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
			if (classInfo.getName() == null) {
				classInfo.setName(name.replace('/', '.'));
			}
			if (superName != null && !"java/lang/Object".equals(superName)) {
				inspectSuperClass(superName, classInfo, modelClassFiles);
			}
		}
		
		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
			classInfo.addMethod(new MethodInfo(access, name, desc));
			return null;
		}
	}
	
	private static void inspectSuperClass(String internalClassName, ClassInfo classInfo, Map<String, File> modelClassFiles) {
		// Look for the superclass in the modelClasses map
		File classFile = modelClassFiles.get(internalClassName.substring(internalClassName.lastIndexOf('/') + 1));
		byte[] fileBytes = null;
		if (classFile != null) {
			fileBytes = getBytesFromFile(classFile);
		} else {
			// Otherwise searching on the classpath
			URL classResource = ClassInspectionUtil.class.getClassLoader().getResource(internalClassName.concat(".class"));
			if (classResource != null) {
				fileBytes = getBytesFromURL(classResource);
			} else {
				throw new BuildException("Class " + internalClassName + " not found neither in the model classes fileset nor on the classpath");
			}
		}
		
		ClassReader cr = new ClassReader(fileBytes);
		cr.accept(new LookupClassVisitor(classInfo, new ClassWriter(0), modelClassFiles), 0);
	}
	
	public static ClassInfo inspectClass(Map<String, File> modelClassFiles, String modelTypeName) {
		File classFile = modelClassFiles.get(modelTypeName);
		ClassReader classReader = new ClassReader(getBytesFromFile(classFile));
		ClassInfo classInfo = new ClassInfo();
		classReader.accept(new LookupClassVisitor(classInfo, new ClassWriter(0), modelClassFiles), 0);
		return classInfo;
	}
	
	private static byte[] getBytesFromURL(URL url) {
		try {
			InputStream is = url.openStream();
			
			int count;
			byte data[] = new byte[BUFFER_SIZE];
			
			// Write the file to the file system
			ByteArrayOutputStream bos = new ByteArrayOutputStream(BUFFER_SIZE);
			while ((count = is.read(data, 0, BUFFER_SIZE)) != -1) {
				bos.write(data, 0, count);
			}
			bos.flush();
			bos.close();
			
			return bos.toByteArray();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static byte[] getBytesFromFile(File file) {
		try {
			InputStream is = new FileInputStream(file);
			
			// Get the size of the file
			long length = file.length();
			
			// Create the byte array to hold the data
			byte[] bytes = new byte[(int) length];
			
			// Read in the bytes
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			
			// Ensure all the bytes have been read in
			if (offset < bytes.length) {
				throw new IOException("Could not completely read file " + file.getName());
			}
			
			// Close the input stream and return bytes
			is.close();
			return bytes;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
