package com.ruleta.controllers;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ruleta.logic.RouletteLogic;
import com.ruleta.models.Roulette;
@RestController
@RequestMapping("/ruleta")
public class RouletteController {
	@Autowired
	private RouletteLogic rouletteLogic;
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public Map<Long, Roulette> findAll() {
		
		return rouletteLogic.findAll();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
		try {
			Roulette roulette = rouletteLogic.findById(id);
			if (roulette != null) {
				
				return ResponseEntity.ok(roulette);
			} else {
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe esta ruleta");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existe esta ruleta, pero no es nueva.");
		}
	}
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public Map<String, String> postRuleta() {
		
		return rouletteLogic.save();
	}
	@RequestMapping(value = "/open/{id}", method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<Object> openRuleta(@PathVariable("id") Long id) {
		Roulette roulette = rouletteLogic.findById(id);
		if (roulette != null) {
			roulette = rouletteLogic.openRuleta(roulette);
			if (roulette != null) {
				
				return ResponseEntity.ok(rouletteLogic.findById(id));
			} else {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existe esta ruleta, pero no es nueva.");
			}
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe esta ruleta");
		}
	}
	@RequestMapping(value = "/close/{id}", method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<Object> closeRuleta(@PathVariable("id") Long id) {
		Roulette roulette = rouletteLogic.findById(id);
		if (roulette != null) {
			if (rouletteLogic.closeRuleta(roulette)) {
				
				return ResponseEntity.ok("ok");
			} else {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esta ruleta está cerrada o nueva");
			}
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe esta ruleta");
		}
	}
	@RequestMapping(value = "/betOnRuletaForNumber/{rouletteId}/{numberWin}/{cash}", method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<Object> betOnRuletaForNumber(@RequestHeader("UserId") Long userId,
			@PathVariable("rouletteId") long rouletteId, @PathVariable("numberWin") int numberWin,
			@PathVariable("cash") double cash) {
		Roulette roulette = rouletteLogic.findById(rouletteId);
		if (roulette != null) {
			if (rouletteLogic.betOnRuletaForNumber(roulette, userId, numberWin, cash)) {
				
				return ResponseEntity.ok("ok");
			} else {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Esta ruleta está cerrada o es nueva o este usuario no tiene dinero");
			}
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe esta ruleta");
		}
	}
	@RequestMapping(value = "/betOnRuletaForColor/{rouletteId}/{blackIsWin}/{cash}", method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<Object> betOnRuletaForColor(@RequestHeader("UserId") Long userId,
			@PathVariable("rouletteId") long rouletteId, @PathVariable("blackIsWin") boolean blackIsWin,
			@PathVariable("cash") double cash) {
		Roulette roulette = rouletteLogic.findById(rouletteId);
		if (roulette != null) {
			if (rouletteLogic.betOnRuletaForColor(roulette, userId, blackIsWin, cash)) {
				
				return ResponseEntity.ok("ok");
			} else {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Esta ruleta está cerrada o es nueva o este usuario no tiene dinero");
			}
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe esta ruleta");
		}
	}

}
