package com.beerjournal.datamodel.entity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserCollectionEntity {

	private Optional<String> id = Optional.empty();
	private String name;
	private String userID;
	private Collection<CollectableObjectEntity> objectsInCollection;

	/**
	 * @param name - name of collection
	 * @param id - object identifier on data base
	 * @param userID - identifier of owner
	 * @param objectsInCollection - collection of CollectableObjects
	 */
	public UserCollectionEntity(String name, String id, String userID, Collection<CollectableObjectEntity> objectsInCollection) {
		this.id = Optional.of(id);
		this.name = name;
		this.userID = userID;
		this.objectsInCollection = objectsInCollection;
	}
	
	/**
	 * @param name = name of collection
	 * @param userID - identifier of owner
	 * @param objectsInCollection - collection of CollectableObjects
	 */
	public UserCollectionEntity(String name, String userID, Collection<CollectableObjectEntity> objectsInCollection) {
		this.id = Optional.empty();
		this.name = name;
		this.userID = userID;
		this.objectsInCollection = objectsInCollection;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<CollectableObjectEntity> getObjectsInCollection() {
		return objectsInCollection;
	}

	public void setObjectsInCollection(Collection<CollectableObjectEntity> objectsInCollection) {
		this.objectsInCollection = objectsInCollection;
	}
	
	public void addCollectableObject(CollectableObjectEntity entity) {
		if (Objects.isNull(objectsInCollection)) {
			objectsInCollection = new LinkedList<>();
		}
		objectsInCollection.add(entity);
	}
	
	public void removeObjectFromCollection(CollectableObjectEntity entity) {
		if (Objects.nonNull(objectsInCollection)) {
			objectsInCollection.remove(entity);
		}
	}
	
	/**
	 * 
	 * @return data base identifier if object is already persisted
	 */
	public Optional<String> getId() {
		return id;
	}
	
	/**
	 * DO NOT POPULATE - id is automatically set during persisting process
	 * @param id - object identifier on data base
	 */
	public void setId(String id) {
		this.id = Optional.of(id);
	}

	@Override
	public String toString() {
		String objects = String.join("", objectsInCollection.stream().map(CollectableObjectEntity::toString).collect(Collectors.toList()));
		return "UserCollectionEntity [id=" + id + ", name=" + name + ", userID=" + userID + ", objectsInCollection="
				+ objects + "]";
	}	
}
