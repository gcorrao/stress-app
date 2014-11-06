package stress.app

class StressController {
    def index() { 
		def start = System.currentTimeMillis()
		def size = Integer.parseInt(params.size)
		def wait = Integer.parseInt(params.wait)
		def cpu  = Integer.parseInt(params.cpu)
		
		println "CPU: $cpu WAIT: $wait SIZE: $size"
		
		def out = buildDummyOutput(size)
		consumeCpu(start, cpu)
		sleepRemainingTime(start, wait)
		
		render out.toString(	)
	}
	
	
	// CPU Burn method
	def consumeCpu(start, cpu) {
		def i = 0
		while(true) {
			if(!(i%1000) && !remainingTimeTo(start, cpu))
				break;
		}
	}
	
	// Calculates remaining time to a given objective
	def remainingTimeTo(start, time) {
		def now = System.currentTimeMillis();
		def diff = now - (start + time)
		diff = diff > 0 ? diff : 0
	}
	
	// Builds a dummy output with random characters
	def buildDummyOutput(size) {
		StringBuffer buff = new StringBuffer();
		size /= 10
		size.times() {
			buff.append("0123456789")
		}
		buff.toString()
	}
	
	
	def sleepRemainingTime(start, time) {
		def toSleep;
		
		while(toSleep = remainingTimeTo(start, time)) {
			Thread.sleep(toSleep)
		}
	}
}
