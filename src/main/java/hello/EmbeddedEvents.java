package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddedEvents{
	
	private ListOfEvents _embedded;
	
	public EmbeddedEvents() {
		_embedded = new ListOfEvents();
	}

	public ListOfEvents get_embedded() {
		return _embedded;
	}

	public void set_embedded(ListOfEvents _embedded) {
		this._embedded = _embedded;
	}
}
