package publicTools.Security.Filter.dao;

import java.util.ArrayList;
import java.util.List;

import publicTools.Security.Filter.bean.Resources;

public class ResourcesDao {

	public List<Resources> findAll()
	{
		List<Resources> list = new ArrayList<Resources>();
		Resources res1 = new Resources();
		res1.setName("admin");
//		res1.setUrl("/view/admin/*");
		res1.setUrl("/testlist/*");
		
		
		list.add(res1);
		return list;
		
	}

}
