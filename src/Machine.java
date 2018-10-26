import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Machine {
	private static final int INF = 10_000_000;
	private static final String EMPTY = "";
	private static final String NO_ANSWER = "NO ANSWER";
	
	private static final char DIVIDE2 = 'D';
	private static final char MULTIPLY2 = 'M';
	private static final char SORT = 'S';
	private static final char TROS = 'T';
	
	private static boolean[] isVisited = new boolean[INF];

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		
		System.out.println(bfs(num));
	}

	private static class NumberProcess {
		int num;
		String str;

		public NumberProcess(int num, String str) {
			this.num = num;
			this.str = str;
		}
	}

	private static String bfs(String str) {
		Queue<NumberProcess> q = new LinkedList();
		int num = Integer.parseInt(str);
		
		q.offer(new NumberProcess(num, EMPTY));
		
		isVisited[num] = true;

		while (!q.isEmpty()) {
			NumberProcess current = q.poll();
			
			int mul = current.num * 2;
			mul = mul >= INF ? mul % INF : mul;
			
			int div = current.num / 2;
			char[] tmp = String.valueOf(current.num).toCharArray();
			Arrays.sort(tmp);
			
			StringBuilder next = new StringBuilder();
			StringBuilder ascSb = new StringBuilder();
			StringBuilder descSb = new StringBuilder();

			for (int i = 0; i < tmp.length; i++) {
				ascSb.append(tmp[i]);
			}
			
			for (int i = tmp.length - 1; i >= 0; i--) {
				descSb.append(tmp[i]);
			}
			
			int asc = Integer.parseInt(ascSb.toString());
			int desc = Integer.parseInt(descSb.toString());

			if (!isVisited[div]) {
				isVisited[div] = true;
				next.append(current.str).append(DIVIDE2);
				
				if (div == 1) return next.toString();
				q.offer(new NumberProcess(div, next.toString()));
			}
			
			next = new StringBuilder();

			if (!isVisited[mul]) {
				isVisited[mul] = true;
				next.append(current.str).append(MULTIPLY2);
				
				q.offer(new NumberProcess(mul, next.toString()));
			}
			
			next = new StringBuilder();

			if (!isVisited[asc]) {
				isVisited[asc] = true;
				next.append(current.str).append(SORT);
				
				if (asc == 1) return next.toString();
				q.offer(new NumberProcess(asc, next.toString()));
			}
			
			next = new StringBuilder();

			if (!isVisited[desc]) {
				isVisited[desc] = true;
				next.append(current.str).append(TROS);
				
				if (desc == 1) return next.toString();
				q.offer(new NumberProcess(desc, next.toString()));
			}
		}
		
		return NO_ANSWER;
	}
}
