package probleme1;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key)
	{
		return (items[key] != null);
	}

	public boolean containsValue(AnyType x )
	{
		return (items[getKey(x)] != null);
	}

	public void remove (AnyType x) {
		if(items[getKey(x)] != null)
			items[getKey(x)] = null;
	}

	public int getKey (AnyType x) {
		return ((a*x.hashCode()+b) %p ) % (this.Size());	
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			items = (AnyType[])new Object[0];
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
			items = (AnyType[])new Object[1];
			items[0] = array.get(0);
			return;
		}

		items = (AnyType[])new Object[array.size()*array.size()];
		boolean fini = false;
		while(!fini) {
			a = generator.nextInt(p-1) + 1;
			b = generator.nextInt(p);
			for(int i = 0; i < array.size(); i++){
				int pos = ((a*array.get(i).hashCode()+b) % p ) % (this.Size());
				
				if(items[pos] != null) {
					this.makeEmpty();
					break;
				}
				items[pos] = array.get(i);
				
				if(i == array.size()-1)
					fini = true;
			}
		}
	}

	
	
	public String toString () {
		String result = "";
		
		for(int i = 0; i < this.Size(); i++) {
			if(items[i] != null) {
				result += "(" + i + "," + items[i] + "), ";
			}
		}
		if(result != "")
			result = result.substring(0, result.length()-2) + '.';
		return result; 
	}

	public void makeEmpty () {
		for(AnyType item : items)
			remove(item);
   	}
}
