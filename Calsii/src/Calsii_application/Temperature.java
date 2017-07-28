package Calsii_application;

public class Temperature {
	
	
	HelperUNumber help=new HelperUNumber();
	UNumber value1,value2;
	String unit1,unit2,measurement,resultUnit;
	UNumber thirtTwo=new UNumber(32);
	UNumber nineByFive=new UNumber(1.8);
	UNumber fiveByNine=new UNumber(0.5555555555);
	UNumber twoSeventyThree=new UNumber(273);
	
	
	public UNumber temperature(String unit1,UNumber value1,String unit2,UNumber value2,String resultUnit,String operation)
	{
		value1=convertTemperature(value1,unit1);
		value2=convertTemperature(value2,unit2);
		UNumber temp=new UNumber(value1);
		if(operation.equals("+"))
			temp.add(value2);
		else if(operation.equals("-"))
			temp.sub(value2);
		else if(operation.equals("*"))
			temp.mpy(value2);
		else
			temp.div(value2);
		
		return convertResultTemperature(temp,resultUnit);
	}
	
	public UNumber convertResultTemperature(UNumber value,String resultUnit) {
		if(resultUnit.equals("Fahrenheit"))
		{
			return value;
		}
		else if(resultUnit.equals("Celsius"))
		{
			value.sub(thirtTwo);
			value.mpy(fiveByNine);
			return value;
		}
		else
		{
			value.sub(thirtTwo);
			value.mpy(fiveByNine);
			value.add(twoSeventyThree);
			return value;
		}
		
	}


	public UNumber convertTemperature(UNumber value,String unit)
	{
		if(unit.equals("Fahrenheit"))
		{
			return value;
		}
		else if (unit.equals("Celsius"))
		{
			value.mpy(nineByFive);
			value.add(thirtTwo);
			return value;
		}
		else
		{
			UNumber onePointEight=new UNumber(1.8);
			value.sub(twoSeventyThree);
			value.mpy(onePointEight);
			value.add(thirtTwo);
			return value;
//			return kelvinToFahrenheit(value);
		}
		
	}
	public UNumber celsiusToFahrenheit(UNumber value)
	{
		value.mpy(nineByFive);
		value.add(thirtTwo);
		return value; 	
	}
	
	public UNumber kelvinToFahrenheit(UNumber value)
	{
		UNumber un=celsiusToFahrenheit(value);
		un.add(twoSeventyThree);
		return un;
	}


}
