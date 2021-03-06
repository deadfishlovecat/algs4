package Chapter1_5;

import java.nio.file.Path;

import edu.princeton.cs.algs4.In;


public class UF {
	//保存该节点所在的连接
	private int []id;
	//节点的个数
	private int count;
	//节点所在根节点的数目
	private int []sz;
	//初始化一个有n个节点的
	public UF(int n) {
		// TODO Auto-generated constructor stub
		count = n;
		id = new int[n];
		sz = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] =i;
		}
	}
	
	//连接节点p和q
	public void union(int p, int q)
	{
		weighted_quick_union(q,p);
	}
	
	//寻找节点p所在的连接分量
	public int quick_find(int p)
	{
		return id[p];
	}
	
	public int quickUnionFind(int p)
	{
		while(p!=id[p]) p = id[p];
		return p;
	}
	
	//quick_find方法需要的union
	private void quick_find_union(int q, int p)
	{
		int qId = quick_find(q);
		int pId = quick_find(p);
		//已经合并了
		if(pId == qId)
		{
			return ;
		}
		for(int i=0; i <id.length; i++)
		{
			if (id[i]==qId)
			{
				id[i] = pId;
			}
		}
		count--;
	}
	
	//普通的quick_union函数
	private void quick_union(int p, int q)
	{
		int pRoot = quickUnionFind(p);
		int qRoot = quickUnionFind(q);
		if(pRoot == qRoot)return;
		id[pRoot] = qRoot;
		count--;
	}
	//j加权的quick_union函数
	
	private void weighted_quick_union(int q, int p)
	{
		int pRoot = quickUnionFind(p);
		int qRoot = quickUnionFind(q);
		if(pRoot == qRoot)return;
		if(sz[pRoot]>sz[qRoot])
		{
			id[qRoot] = id[pRoot];
			sz[pRoot] += sz[qRoot];
		}
		else {
			id[pRoot] = id[qRoot];
			sz[qRoot] = sz[pRoot];
		}
		count--;
	}
	
	
	//判断p和q是否是同一个连接
	public boolean connected(int p, int q)
	{
		return id[p] == id[q];
	}
	
	//返回连接的个数
	public int count()
	{
		return count;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Path ="C:\\code\\java_code\\algs4-data\\tinyUF.txt";
		In inputIn = new In(Path);
		int N = inputIn.readInt();
		UF uf = new UF(N);
		while(!inputIn.isEmpty())
		{
			int p = inputIn.readInt();
			int q = inputIn.readInt();
			if(uf.connected(p, q)) continue;
			uf.union(p, q);
			System.out.println(p+" "+q);
		}
		System.out.println(uf.count()+"componentes");
	}

}
