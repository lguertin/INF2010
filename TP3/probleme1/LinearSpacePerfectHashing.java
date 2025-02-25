package probleme1;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			data = new QuadraticSpacePerfectHashing[0];
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;

			data = new QuadraticSpacePerfectHashing[1];
			data[0].SetArray(array);
			return;
		}
		
		a = generator.nextInt(p-1) + 1;
		b = generator.nextInt(p);
		
		data = new QuadraticSpacePerfectHashing[array.size()];
		//Create a ...
		for(int i = 0; i< array.size();i++){
			
			int pos = getKey(array.get(i));
			ArrayList<AnyType> temp = new ArrayList<AnyType>();
			temp.add(array.get(i));
			
			if(data[pos] != null) {
				for(int j = 0; j < data[pos].Size(); j++) {
					if(data[pos].containsKey(j)){
						temp.add(data[pos].items[j]);
					}
				}
			}
			
			data[pos] = new QuadraticSpacePerfectHashing<AnyType>(temp);

		}
	}

	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		return (data[key]!= null); 

	}
	
	public int getKey(AnyType x) {
		return (((a*x.hashCode() +b)% p)% data.length);
		
	}
	
	public boolean containsValue (AnyType x) {
		if (data[getKey(x)] == null)
			return false;
		else
			return data[getKey(x)].containsValue(x);
	}
	
	public void remove (AnyType x) {
		if(containsValue(x)){
			data[getKey(x)].remove(x);
		}
			
			
		
		
	}
	public String toString () {
		String result = "";
		for(int i = 0 ; i< data.length;i++){
			if(data[i]!= null)
				result+="Cle "+ i + " : "+ data[i].toString()+ "\n";
		}
		// A completer
		
		
		return result; 
	}

	public void makeEmpty () {
		for(QuadraticSpacePerfectHashing<AnyType> listQ : data){
				listQ.makeEmpty();
		}
   	}
	
}
