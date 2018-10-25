import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tester {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int loop = 10;
		int[] num = new int[loop];
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < loop; i++) num[i] = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < loop; i++) {
			int n = Integer.parseInt(br.readLine());
			
			if(num[i] != n) {
				System.out.println(i + " " + n + " " + num[i] + " " + false);
			}
		}
		
//		String[] str = new String[loop];
//		for(int i = 0; i < loop; i++) {
//			str[i] = br.readLine();
//		}
//		
//		System.out.println(str[248]);
		
		System.out.println(true);
	}
}
