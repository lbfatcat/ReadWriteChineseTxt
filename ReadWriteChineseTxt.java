import java.io.*;

public class ReadWriteChineseTxt
{  
	File inFile;
	File dataInfile, dataOutFile, logFile;
	BufferedWriter logWriter;
	int logLineCount=0;
	
	class LineEmptyException extends Exception
	{
		String ErrorMessage;
		public LineEmptyException(String ErrMsg)
		{
			ErrorMessage= ErrMsg;
		}
		public String getMessage()
		{
			return this.ErrorMessage;
		}
	}
	
	public ReadWriteChineseTxt(String configureFilePath)
	{
		BufferedReader cfgReader=null;
		inFile= new File(configureFilePath);
		
		try {
			cfgReader= new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
		}catch (FileNotFoundException e) 
        {  
            System.out.println("configuration file is not found.");  
        }
		
		String firstLine="", secondLine="", thirdLine="";
		
		try {
			firstLine= cfgReader.readLine();
			if(firstLine==null) throw new LineEmptyException("Error: first empty line read!");
		}catch(LineEmptyException lee) {
			System.out.println(lee.getMessage());
		}catch (IOException e) 
        {  
            System.out.println("Read Exceptioned");  
        }
		
		this.dataInfile= new File(this.ExtractConfigureFileName(firstLine));
		
		try {
			secondLine= cfgReader.readLine();
			if(secondLine==null) throw new LineEmptyException("Error: second empty line read!");
		}catch(LineEmptyException lee) {
			System.out.println(lee.getMessage());
		}catch (IOException e) 
        {  
            System.out.println("Read Exceptioned");  
        }
		
		this.dataOutFile= new File(this.ExtractConfigureFileName(secondLine));
		
		try {
			thirdLine= cfgReader.readLine();
			if(thirdLine==null) throw new LineEmptyException("Error: third empty line read!");
		}catch(LineEmptyException lee) {
			System.out.println(lee.getMessage());
		}catch (IOException e) 
        {  
            System.out.println("Read Exceptioned");  
        }
		
		this.logFile= new File(this.ExtractConfigureFileName(thirdLine));
		
		try{
			this.logWriter= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile)));
		}catch (FileNotFoundException e) 
        {  
            System.out.println("log file is not fond");  
        } 
		
	}
	
	String ExtractConfigureFileName(String cfgLine)
	{
		String fileName="";
		fileName= cfgLine.substring(cfgLine.indexOf("=")+1);
		return fileName;
	}
	
	void writeLog(String logMsg)
	{
		try {
			this.logWriter.write(logMsg+this.logLineCount+"\r\n");
			this.logLineCount++;
			if(this.logLineCount==1133)
			{
				System.out.println("Reaching edge point");
			}
			if(this.logLineCount>1133)
				System.out.println("log written "+ this.logLineCount+": "+logMsg+"\r\n");
			if(this.logLineCount%10==0)
				System.out.println("Log line Count"+this.logLineCount);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return;
	}
	
	public static void main(String [] args)
	{  
		ReadWriteChineseTxt ins= new ReadWriteChineseTxt("configure.txt");
		
		BufferedReader dataReader=null;
		BufferedWriter dataWriter=null;
		
		try {
			dataReader = new BufferedReader(new InputStreamReader(new FileInputStream(ins.dataInfile)));
			dataWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ins.dataOutFile)));
			
			String line="";
			int lineCount=0;
			
			while((line= dataReader.readLine())!=null )
			{
				ins.writeLog(line);
				lineCount++;
				if(lineCount%10==0)
					System.out.println("line count: "+ lineCount);
			}
			System.out.println("final line count: "+ lineCount);
			
		}catch (FileNotFoundException e){  
            System.out.println("file is not fond");  
        }catch (IOException e){  
            System.out.println("Read or write Exceptioned");  
        }finally {
        	if(null!= dataReader)
        	{
        		try {
        			dataReader.close();
        		}catch(IOException ioe) {
        			ioe.printStackTrace();
        		}
        	}
        	if(null!= dataWriter)
        	{
        		try {
        			dataWriter.close();
        		}catch(IOException ioe) {
        			ioe.printStackTrace();
        		}
        	}
        }
	}
}  