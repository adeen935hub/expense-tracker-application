package com.expensetracker.common.persistence.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.common.dto.FilterDto;
import com.expensetracker.common.interfaces.IService;
import com.expensetracker.common.persistence.ServicePreconditions;
import com.expensetracker.common.persistence.specification.RawSpecification;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

@Transactional
public abstract class AbstractRawService<T> implements IService<T> {

	@Autowired
	protected ApplicationEventPublisher eventPublisher;

	public AbstractRawService() {
		super();
	}

	@Override
	@Transactional(readOnly = true)
	public T findOne(final long id) {
		if (id == 0)
			return null;
		Optional<T> entity = getDao().findById(id);
		return entity.orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public T findOne(Specification<T> spec) {

		List<T> results = Lists.newArrayList(getSpecificationExecutor().findAll(spec));
		if (!results.isEmpty()) {
			if (results.size() > 1) {
				throw new IllegalArgumentException("There are more than one entity with size " + results.size());
			}
			return results.get(0);
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public T findOne(Specification<T> spec, Sort sort) {
		List<T> results = Lists.newArrayList(getSpecificationExecutor().findAll(spec, sort));

		if (!results.isEmpty()) {
			if (results.size() > 1) {
				throw new IllegalArgumentException("There are more than one entity with size " + results.size());
			}
			return results.get(0);
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public T findOne(FilterDto filter) {
		RawSpecification<T> spec = new RawSpecification<>(filter);
		return findOne(spec);
	}

	@Override
	@Transactional(readOnly = true)
	public T findOne(FilterDto filter, boolean sorting) {
		RawSpecification<T> spec = new RawSpecification<>(filter);
		if (sorting) {
			return findOne(spec, filter.getSortOrders());
		} else {
			return findOne(spec);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return Lists.newArrayList(getDao().findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll(Specification<T> spec) {
		return Lists.newArrayList(getSpecificationExecutor().findAll(spec));
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll(Specification<T> spec, Sort sort) {
		return Lists.newArrayList(getSpecificationExecutor().findAll(spec, sort));
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll(FilterDto filter) {
		RawSpecification<T> spec = new RawSpecification<>(filter);
		return findAll(spec);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll(FilterDto filter, boolean sorting) {
		RawSpecification<T> spec = new RawSpecification<>(filter);
		if (sorting) {
			return findAll(spec, filter.getSortOrders());
		} else {
			return findAll(spec);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<T> findAll(Pageable pageable) {
		return getDao().findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<T> findAll(Integer size, Integer pageNo, FilterDto filter) {
		Pageable pageable = PageRequest.of(pageNo - 1, size, filter.getSortOrders());
		RawSpecification<T> spec = new RawSpecification<>(filter);
		return getSpecificationExecutor().findAll(spec, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<T> findAll(Integer size, Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, size);
		return getDao().findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return getSpecificationExecutor().findAll(spec, pageable);
	}

	@Override
	public List<T> findAllById(List<Long> ids) {
		Iterable<T> list = getDao().findAllById(ids);
		return StreamSupport.stream(list.spliterator(), false).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<?> findAllWithDto(Specification<T> spec, Pageable pageable) {
		return getSpecificationExecutor().findAll(spec, pageable);
	}

	// save/create/persist
	@Override
	public T create(final T entity) {
		Preconditions.checkNotNull(entity);

		final T persistedEntity = getDao().save(entity);

		return persistedEntity;
	}

	// update/merge
	@Override
	public T update(final T entity) {
		Preconditions.checkNotNull(entity);

		final T persistedEntity = getDao().save(entity);
		return persistedEntity;
	}

	// delete
	@Override
	public void deleteAll() {
		getDao().deleteAll();
	}

	@Override
	public void deleteAll(List<T> entities) {
		getDao().deleteAll(entities);
	}

	public void delete(final long id) {
		final Optional<T> entity = getDao().findById(id);
		if (entity.isPresent()) {
			ServicePreconditions.checkEntityExists(entity);
			getDao().delete(entity.get());
		}
	}

	@Override
	// count
	public long count(FilterDto filter) {
		RawSpecification<T> spec = new RawSpecification<>(filter);
		return getSpecificationExecutor().count(spec);
	}

	@Override
	// count
	public long count() {
		return getDao().count();
	}

	// template method

	protected abstract JpaRepository<T, Long> getDao();

	protected abstract JpaSpecificationExecutor<T> getSpecificationExecutor();

}
