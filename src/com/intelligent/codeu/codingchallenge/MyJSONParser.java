// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.intelligent.codeu.codingchallenge;

import java.io.IOException;

final class MyJSONParser implements JSONParser {
	String key[] = new String[100];
	String val[] = new String[100];
	int k=0, v=0;
	public JSON check(String in, int i) throws IOException
{
	//System.out.println(in.charAt(i));
		while(in.charAt(i)==' ' && i<in.length()-1)
		{
			
			i++;
		}
		if(i!=in.length()-1)
		{
			if(in.charAt(i)=='"')
			{
				i++;
				while(in.charAt(i)!='"' && i<in.length()-1)
				{
					i++;
				}
				if(i==in.length()-1)
				{
					throw new IOException();
				}
				else
				{
					while(in.charAt(i)==' ' && i<in.length()-1)
					{
						
						i++;
					}
					if(i==in.length()-1)
					{
						throw new IOException();
					}
					else
					{
						if(in.charAt(i)!=':')
						{
							throw new IOException();
						}
						else
						{
							while(in.charAt(i)==' ' && i<in.length()-1)
							{
								
								i++;
							}
							if(i==in.length()-1)
							{
								throw new IOException();
							}
							else
							{
								if(in.charAt(i)!='"')
								{
									throw new IOException();
								}
								else
								{
									while(in.charAt(i)!='"' && i<in.length()-1)
									{
										i++;
									}
									if(in.charAt(i)!='"' && i==in.length()-1)
									{
										throw new IOException();
									}
									else
									{
										if(i==in.length()-1)
										{
											return new MyJSON();
										}
										else
										{
											while(in.charAt(i)==' ' && i<in.length()-1)
											{
												
												i++;
											}
											if(i!=in.length()-1)
											{
												if(in.charAt(i)==',')
												{
													return check(in, i+1);
												}
												else
												{
													throw new IOException();
												}
												
											}
											else
											{
												return new MyJSON();
												
											}
												
							

										}
				
									}
								}
							}
							
						}
					}
				}
			}
			else
			{
				throw new IOException();
			}
		}
		else
		{
			return new MyJSON();
		}

	
	
	
	
	
}


int checkKeyValuePair(String in, int i)throws IOException {
	int j;
	
	while(in.charAt(i)==' ' && i<in.length()-1)
	{	
		i++;
	}
	if(i==in.length()-1){
		if(in.charAt(i)!='}'){
			throw new IOException();
		}
		else {
			return i;
		}
	}
	else{
		j = checkString(in, i);
		key[k] = in.substring(i+1, j);
		k++;
		i=j+1;
		while(in.charAt(i)==' ' && i<in.length()-1)
		{	
			i++;
		}
		if(i==in.length()-1){
			throw new IOException();
		}
		else {
			if(in.charAt(i)==':') {
				while(in.charAt(i)==' ' && i<in.length()-1)
				{	
					i++;
				}
				
				if(in.charAt(i)=='{')
				{
					j=checkObject(in,i);
					val[v] = in.substring(i+1, j);
					v++;
					i=j+1;
					return i;
				}
				else if(in.charAt(i)=='"')
				{
					j = checkString(in ,i);
					val[v] = in.substring(i+1, j);
					v++;
					i=j+1;
					return i;
				}
				else{
					throw new IOException();
				}
			}
			else {
				throw new IOException();
			}
		}
	}
}


int checkObject(String in, int i) throws IOException
{ int j;
	if(in.charAt(i)=='{') {
		j=checkKeyValuePair(in, i);
		i=j;
		while(in.charAt(i)==' ' && i<in.length()-1)
		{	
			i++;
		}
		if(i==in.length()-1)
		{
			if(in.charAt(i)=='}')
			{
				return i;
			}
			else
			{
				throw new IOException();
			}
			
		}
		else
		{
			while(i<in.length())
			{
				if(in.charAt(i)==',')
				{
					while(in.charAt(i)==' ' && i<in.length()-1)
				    {	
					i++;
				    }
					j=checkKeyValuePair(in, i);
					i=j;
					while(in.charAt(i)==' ' && i<in.length()-1)
				    {	
					i++;
				    }
					if(i==in.length()-1)
					{
						if(in.charAt(i)!='}')
						{
							throw new IOException();
						}
						else
						{
							return i;
						}
					}
					
				}
				else
				{
					throw new IOException();
				}
			}
			throw new IOException();
		}
		
	}
	else {
		throw new IOException();
	}
}

int checkString(String in,int i) throws IOException
{
		if(in.charAt(i)=='"')
		{
			i++;
			while(in.charAt(i)!='"' && i<in.length()-1)
			{
				i++;
			}
			if(in.charAt(i)!='"'){
				throw new IOException();
			}
			else {
				return i;
			}
		}
		else{
			throw new IOException();
		}
}




  @Override
  public JSON parse(String in) throws IOException {
	System.out.println(in);

	if(in.startsWith("{") && in.endsWith("}")) {
		return check(in,1);
		}
	else{
		throw new IOException();
	}
	

    
  }
}

 