package recipesharing.db;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import recipesharing.logic.Image;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 *
 */
@Repository
public class ImageDao implements MongoRepository<Image, String> {
    @Override
    public <S extends Image> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Image> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Image> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Image> findAll() {
        return null;
    }

    @Override
    public Iterable<Image> findAllById(Iterable<String> strings) {
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
    public void delete(Image entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Image> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Image> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Image> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Image> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Image> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Image> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Image> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Image> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Image> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Image, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Image> S insert(S entity) {
        return null;
    }
}
