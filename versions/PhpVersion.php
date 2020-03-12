<?php

    $left_coil_magnitude;
    $right_coil_magnitude;
    $top_coil_magnitude;
    $bottom_coil_magnitude;
    $total_magnitude;
    $x_pos;
    $y_pos;

    function execute($args){
         global $left_coil_magnitude;
         global $right_coil_magnitude;
         global $top_coil_magnitude;
         global $bottom_coil_magnitude;
         global $total_magnitude;
         global $x_pos;
         global $y_pos;
        if(sizeof($args) == 1)
            die("No arguments provided!");
        else if(sizeof($args) < 4){
            die("Wrong number of arguments");
        }

        if( !(is_numeric($args[2]) && is_numeric($args[3]) && is_numeric($args[4])) )
            die("Wrong type of arguments");
        else{
            $x_pos = $args[2];
            $y_pos = $args[3];
            $total_magnitude = $args[4];

            if($x_pos == 0){
                $left_coil_magnitude = $total_magnitude;
                $right_coil_magnitude = 0;
            }
            else{
                $ratio_x = (1.0 - $x_pos) / $x_pos;
                $left_coil_magnitude = ($ratio_x / ($ratio_x + 1)) * $total_magnitude;
                $right_coil_magnitude = $total_magnitude - $left_coil_magnitude;
            }

            if($y_pos == 0){
                $top_coil_magnitude = $total_magnitude;
                $bottom_coil_magnitude = 0;
            }
            else{
                $ratio_y = (1.0 - $y_pos) / $y_pos;
                $top_coil_magnitude = ($ratio_y / ($ratio_y + 1)) * $total_magnitude;
                $bottom_coil_magnitude = $total_magnitude - $top_coil_magnitude;
            }
        }
    }

           execute($argv);
           echo($top_coil_magnitude)."\n";
           echo($bottom_coil_magnitude)."\n";
           echo($left_coil_magnitude)."\n";
           echo($right_coil_magnitude)."\n";

?>