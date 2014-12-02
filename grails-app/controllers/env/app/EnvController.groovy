package env

class EnvController {
    def index() { 
		render System.getProperties().toString();
	}
}
