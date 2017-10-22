package com.example.markkko.povezime.core.util;

import android.util.Log;


import com.example.markkko.povezime.core.models.BaseEntity;
import com.example.markkko.povezime.core.models.Nameable;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtil {

	private static final String TAG = CollectionUtil.class.getSimpleName();

	public static <T> void replaceItems(final Collection<T> originalList, final Collection<T> replacement) {
		if (originalList == null || replacement == null) {
			Log.w(TAG, "Source or replacement list is null");
			return;
		}
		originalList.clear();
		originalList.addAll(replacement);
	}

	public static <T> void swap(final List<T> items, final T oldItem, final T newItem) {
		if (items == null) {
			Log.w(TAG, "Original list cannot be null");
			return;
		}

		if (oldItem == null || newItem == null) {
			Log.w(TAG, "Old currentItem and new currentItem cannot be null");
			return;
		}

		int oldIndex = items.indexOf(oldItem);
		items.remove(oldIndex);
		items.add(oldIndex, newItem);
	}

	public static <T extends BaseEntity> void swap(final List<T> items, final T newItem) {
		if (items == null) {
			Log.w(TAG, "Original list cannot be null");
			return;
		}

		if (newItem == null) {
			Log.w(TAG, "New currentItem cannot be null");
			return;
		}

		T oldItem = find(items, newItem.getId());
		if (oldItem == null) {
			Log.w(TAG, "Old currentItem cannot be null");
			return;
		}

		int oldIndex = items.indexOf(oldItem);
		items.remove(oldIndex);
		items.add(oldIndex, newItem);
	}

	public static <T> boolean remove(final Collection<T> items, final T itemToRemove) {
		if (items == null) {
			Log.w(TAG, "Items list cannot be null");
			return false;
		}

		if (itemToRemove == null) {
			Log.w(TAG, "Item to remove cannot be null");
			return false;
		}

		return items.remove(itemToRemove);
	}

	public static <T extends BaseEntity> boolean addUnique(final List<T> items, final T itemToAdd) {
		if (items == null) {
			Log.w(TAG, "Items list cannot be null");
			return false;
		}

		if (itemToAdd == null) {
			Log.w(TAG, "Item to remove cannot be null");
			return false;
		}

		T oldItem = find(items, itemToAdd.getId());
		if (oldItem != null) {
			return false;
		}

		return items.add(itemToAdd);
	}


	public static <T extends BaseEntity> T find(Collection<T> entities, long id) {
		if (entities == null) {
			return null;
		}

		for (T entity : entities) {
			if (entity.getId() == id) {
				return entity;
			}
		}

		return null;
	}

	public static <T extends BaseEntity> T remove(Collection<T> entities, long id) {
		if (entities == null) {
			return null;
		}

		T entity = find(entities, id);
		if (entity != null) {
			entities.remove(entity);
			return entity;
		}

		return null;
	}

	public static <T extends Nameable> List<T> sortByNameAsc(List<T> items) {
		if (items == null) {
			return null;
		}

		Collections.sort(items, new Comparator<Nameable>() {
			@Override
      public int compare(Nameable lhs, Nameable rhs) {
				return lhs.getName().compareTo(rhs.getName());
			}
		});

		return items;
	}

	public static <T extends Nameable> T findByName(Collection<T> list, String name) {
		for (T entity : list) {
			if (entity.getName().equalsIgnoreCase(name))
				return entity;
		}
		return null;
	}

	public static <T extends BaseEntity> void addAllSafe(Collection<T> originalList, Collection<T> toAdd) {
		if (originalList != null && toAdd != null && toAdd.size() > 0) {
			originalList.addAll(toAdd);
		}
	}

	public static int size(List list) {
		if (list == null) {
			return 0;
		}
		return list.size();
	}

}
