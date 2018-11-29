import java.io.*;

public class ReadWriteChineseTxt
{  
	public static void main(String [] args)
	{  
        File firstFile = new File("C://tmp//fileone.txt");  
        File secondFile= new File("C://tmp//filesecond.txt");  
        BufferedReader in = null;  
        BufferedWriter out = null;        
        try 
        {          
            in = new BufferedReader(new InputStreamReader(new FileInputStream(firstFile), "gbk"));  
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(secondFile), "gbk"));  
            String line = "";  
            while((line = in.readLine())!=null)
            {  
                System.out.println(line);  
                out.write(line+"\r\n");  
            }  
        } 
        catch (FileNotFoundException e) 
        {  
            System.out.println("file is not fond");  
        } 
        catch (IOException e) 
        {  
            System.out.println("Read or write Exceptioned");  
        }
        finally
        {             
            if(null!=in)
            {   
                try {  
                    in.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }
            }  
            if(null!=out)
            {  
                try {  
                    out.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }
            }
        }
    }  
}  