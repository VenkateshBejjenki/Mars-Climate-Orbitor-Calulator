package Calsii_application;

/**
 * <p> Title: HelperUNumber class </p>
 * 
 * <p> Description: Helper Class for UNumber object creations </p>
 * 
 *<p> Copyright: Venkatesh Bejjenki  © 2017 </p>
 * 
 * @author Venkatesh Bejjenki  
 * @Credits Vikas Reddem
 * @version 1.00	Initial Helper Class for UNumber object creations
 * 
 */
public class HelperUNumber {

	/**********
	 * This method the takes in a string value and generates the string true value of the no removing the '.' character and
	 * also the substring from stating character of the string to the 'E' character. This method returns a String.
	 */
	public String getTrueVal(String s){

		String ss = s.replace(".", "");
		if(ss.contains("E")){
			
			ss=ss.substring(0, ss.indexOf('E'));
			//System.out.println("ss="+ss);

		}
		//System.out.println("ss is "+ss);
		return ss;
	}
	/**********
	 * This method the takes in a string value and the index position of the decimal in the string.
	 */
	public int getDotPoint(String s){
		if (s.contains(".")) {															//
			return s.indexOf('.');			
		}
		else return s.length();

	}
	/**********
	 * This method the takes in a string value and generates the string  from starting character to the inception of 
	 * '.' character. This method returns a String.
	 */
	public String getInteger(String s) {
		if(s.contains("."))
			return s.substring(0, getDotPoint(s));
		else
			return s;
	}

	/**********
	 * This method the takes in a string value and generates a rounded value of the given string.
	 * For example 2.645E+3 ==> 2645. and  2.645E-3 ==> 0.002645
	 * This method returns a String.
	 */
	public String roundTo(UNumber un) {
		String s = un.toString();
		System.out.println("in helper round to"+un.toString());
		int i;
		for(i=s.length()-1;i>0;i--){
			if(s.charAt(i)=='+' || s.charAt(i)=='-'){
				break;
			}
		}

		if(s.charAt(i)=='+'){
			int exp=Integer.parseInt(s.substring(i+1));
			if(exp==0){
				return s.substring(0, s.indexOf('E'));
			}
			if(exp<15){
				String num=getTrueVal(s);
				return putDecimalAfter(exp+1,num);
			}
			return un.toString();
		}
		else{
			int exp=Integer.parseInt(s.substring(i+1));
			if(exp<5){
				//				System.out.println("hi");
				char sign=s.charAt(0);
				String second=s.substring(s.indexOf('.')+1,s.indexOf('E'));
				if(exp==0){
					System.out.println("hi");
					second = 0+second;
				}
				for(i=0;i<exp;i++){
					second = 0+second;
				}
				second=sign+"0."+second;
				return second;
			}
			//			System.out.println("hi");
			return un.toString();
		}
	}

	/**********
	 * This method the takes in an integer value and a string, in turn generates string with '.' character appended at 
	 * given integer value and returns the generated a String.
	 */
	private String putDecimalAfter(int exp, String num) {
		String res="";
		for(int i=0;i<num.length();i++){
			res+=num.charAt(i);
			if(i==exp){
				res+='.';
			}
		}

		if(res.charAt(res.length()-1)=='.'){
			return res.substring(0, 1)+res.substring(2, res.length()-1);
		}
		return res.substring(0, 1)+res.substring(2);		
	}

	/**********
	 * This method the takes in a string and computes the E values and generates the associated equivalent string value without E.
	 * thus generated String is returned.
	 */
	public String convertE(String s){
		
		if(s.indexOf('E')!=-1){
			
			String n=s.substring(0, s.indexOf('E'));
			char sign=s.charAt(s.indexOf('E')+1);
			String exp=s.substring(s.indexOf('E')+2);
			int dot=n.length();
			if(s.indexOf('.')!=-1){
				dot=s.indexOf('.');
			}
			String num=getTrueVal(n);
			int temp=num.length()+dot;
			int negDot=num.length()-dot;
			//   System.out.println(negDot);
			int check=Integer.parseInt(exp)+dot;
			//System.out.println(check);
			for(int i=0;i<check+temp+1;i++){
				if(num.length()<check+temp+1){
					if(sign=='+'){
						num+="0";
					}
					else{
						num="0"+num;
					}
				}

				else
					break;
			}

			String result="";
			if(sign=='+')   
				for(int i=0;i<num.length();i++){
					result+=num.charAt(i);
					if(i+1==check){
						result+=".";
					}
				}
			else{
				int count=1;
				check=Integer.parseInt(exp)+negDot;
				//	    System.out.println(check);
				for(int i=num.length()-1;i>=0;i--){
					result=num.charAt(i)+result;
					if(count==check){
						result="."+result;
					}
					count++;
				}
			}

			String op="";
			if(result.indexOf('+')!=-1 || result.indexOf('-')!=-1){
				op=result.charAt(0)+"";
				result=result.substring(1);
			}
			String res="";
			for(int i=0;i<result.length();i++){
				if(result.charAt(i)!='0'){
					res=result.substring(i);
					break;
				}
			}

			if(res.charAt(0)=='.'){
				res='0'+res;
			}


			for(int i=res.length()-1;i>=0;i--){
				if(res.charAt(i)!='0'){
					res=res.substring(0, i+1);
					break;
				}
			}

			if(res.charAt(res.length()-1)=='.'){
				return op+res+'0';
			}
			return op+res;
		}
		return s;
	}

	public static void main(String[] args) {
		HelperUNumber un = new HelperUNumber();
		System.out.println(un.getTrueVal("22.58"));
		System.out.println(un.getDotPoint("22.58"));
		System.out.println(un.getInteger("22.58"));
	}


}
