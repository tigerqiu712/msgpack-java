//
// MessagePack for Java
//
// Copyright (C) 2009-2011 FURUHASHI Sadayuki
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
package org.msgpack.template;

import java.util.List;
import java.util.ArrayList;


public class FieldList {
    public static class Entry {
	private String name;

	private FieldOption option;

	public Entry() {
	    this(null, FieldOption.IGNORE);
	}

	public Entry(final String name, final FieldOption option) {
	    this.name = name;
	    this.option = option;
	}

	public String getName() {
	    return name;
	}

	public FieldOption getOption() {
	    return option;
	}

	public boolean isAvailable() {
	    return option != FieldOption.IGNORE;
	}

	public boolean isRequired() {
	    return option == FieldOption.REQUIRED;
	}

	public boolean isOptional() {
	    return option == FieldOption.OPTIONAL;
	}

	public boolean isNullable() {
	    return option == FieldOption.NOTNULLABLE;
	}
    }

    private ArrayList<Entry> list;

    public FieldList() {
	list = new ArrayList<Entry>();
    }

    public void add(final String name) {
	add(name, FieldOption.REQUIRED);
    }

    public void add(final String name, final FieldOption option) {
	list.add(new Entry(name, option));
    }

    public void put(final int index, final String name) {
	put(index, name, FieldOption.REQUIRED);
    }

    public void put(final int index, final String name, final FieldOption option) {
	if (list.size() < index) {
	    do {
		list.add(new Entry());
	    } while (list.size() < index);
	    list.add(new Entry(name, option));
	} else {
	    list.set(index, new Entry(name, option));
	}
    }

    public List<Entry> getList() {
	return list;
    }
}
