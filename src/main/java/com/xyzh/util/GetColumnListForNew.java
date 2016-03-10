package com.xyzh.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xyzh.entity.Columns;

/**
 * @author 作者：       周卫东
 * @E-mail 邮箱：       zhouweidong@gochinatv.com
 * @version 创建时间：2016年3月8日 下午3:52:37
 * 类说明
*/
public class GetColumnListForNew {
	public static List<Columns> getColumnList(List<Map<String, Object>> cloumnsList){
		List<Columns> columns_List = new ArrayList<Columns>();
		for (Map<String, Object> mapColumns : cloumnsList) {
			List<String> keyWord_list = GetKeyWordList.getKeyWordByColumns(mapColumns.get("cnName").toString(), 0, 1000);// aa[0]
			System.out.println(mapColumns.get("cnName").toString());
			System.out.println(keyWord_list.size());
			if (null == keyWord_list || keyWord_list.size() == 0) {
				continue;
			}
			String json = GetJson_StrForNew.getJson_Str(mapColumns.get("cnName").toString(), keyWord_list);
			Columns columns = new Columns();
			columns.setCnName(mapColumns.get("cnName").toString());
			columns.setParentId(mapColumns.get("parentId").toString());
			columns.setCnId(mapColumns.get("Id").toString());
			columns.setJson(json);
			columns_List.add(columns);
		}
		return columns_List;
	}
}
