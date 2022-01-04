package com.dal.mongodb;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @PackageName: com.dal.mongodb
 * @ClassName: UserRepositoryImpl
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/1/4 4:37 下午
 */
@Service
public class UserRepositoryImpl implements UserRepository{
    @Override
    public List<UserDO> findByUsername(String username) {
        return null;
    }

    @Override
    public List<UserDO> findByUsernameLike(String username) {
        return null;
    }

    @Override
    public <S extends UserDO> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserDO> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserDO> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<UserDO> findAll() {
        return null;
    }

    @Override
    public Iterable<UserDO> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(UserDO entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserDO> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserDO> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserDO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserDO> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends UserDO> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends UserDO> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserDO> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserDO> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserDO> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserDO> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserDO> boolean exists(Example<S> example) {
        return false;
    }
}
