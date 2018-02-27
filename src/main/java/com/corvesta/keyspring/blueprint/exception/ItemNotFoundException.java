package com.corvesta.keyspring.blueprint.exception;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {

	  private final Long itemtId;

	  public ItemNotFoundException(final long id) {
	    super("Item could not be found with itemtId: " + id);
	    this.itemtId = id;
	  }

	public Object getItemtId() {
		return this.itemtId;
	}


}
