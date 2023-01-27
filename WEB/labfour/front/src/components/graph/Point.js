import React from "react";

export default function Point(props) {
    let className = `figure ${props.isHit ? 's' : 'f'}`
    return (
        <circle className={className} cx={600 + Math.round(props.coordX*100)} cy={600 - Math.round(props.coordY*100)} r="10"/>
    )
}