import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-progress-bar',
  templateUrl: './progress-bar.component.html',
  styleUrls: ['./progress-bar.component.scss']
})
export class ProgressBarComponent implements OnInit {

  @Input() fase: number = 2;

  constructor() { }

  ngOnInit(): void {
    // Al cargar la componente, debemos indicar en qué fase estamos
    const dots: NodeListOf<HTMLDivElement> = document.querySelectorAll('.dot');
    const fases: NodeListOf<HTMLDivElement> = document.querySelectorAll('.nombre-fase');
    dots[this.fase-1].classList.add("active");
    fases[this.fase-1].classList.add("active");
  }

}
