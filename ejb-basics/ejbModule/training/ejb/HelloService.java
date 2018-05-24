package training.ejb;

import javax.ejb.Remote;

@Remote
public interface HelloService {
	public String sayHello(String name);
}
