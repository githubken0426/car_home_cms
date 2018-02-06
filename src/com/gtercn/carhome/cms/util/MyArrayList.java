package com.gtercn.carhome.cms.util;

import java.util.ArrayList;
import java.util.Iterator;

public class MyArrayList<E> extends ArrayList<E> {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Iterator<E> it = iterator();
		boolean hasNext = it.hasNext();
		while (hasNext) {
			E str = it.next();
			sb.append(str);
			hasNext = it.hasNext();
			if (hasNext)
				sb.append(",");
		}
		return sb.toString();
	}
}
