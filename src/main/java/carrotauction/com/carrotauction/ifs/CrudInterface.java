package carrotauction.com.carrotauction.ifs;

import carrotauction.com.carrotauction.network.Header;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudInterface<Req, Res> {

    Header<List<Res>> search(Pageable pageable);

    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
