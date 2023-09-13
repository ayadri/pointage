package com.example.backendpointage.Service;

import com.example.backendpointage.enums.StatusPointage;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class PauseService {
    public Duration calculerDureePause(List<StatusPointage> pointages) {
        Duration dureePause = Duration.ZERO;
        boolean debutTrouve = false;

        for (StatusPointage pointage : pointages) {
            if (pointage == StatusPointage.DEBUTPAUSE) {
                debutTrouve = true;
            } else if (pointage == StatusPointage.FINPAUSE && debutTrouve) {
                // Calcul de la durée de la pause si on a trouvé un "DEBUTPAUSE" précédent
                dureePause = dureePause.plusMinutes(30); // Par exemple, 30 minutes
                debutTrouve = false; // Réinitialisation pour rechercher la prochaine pause
            }
        }

        return dureePause;
    }


}
