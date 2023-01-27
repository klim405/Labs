import React from "react";
import Point from "./Point";

export default function PointsLayer(props) {
    return (
        <g>
            {props.hitInfos.filter(
                (hitInfo) => props.selectedRadius.includes(hitInfo.radius)
            ).map((hitInfo) => (
                <Point isHit={hitInfo.isHit} coordX={hitInfo.coordX} coordY={hitInfo.coordY} key={hitInfo.id}/>
            ))}
        </g>
    );
}