import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  usuariologado: string;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.usuariologado = this.authService.getUsuarioAutenticado();
  }

  logout() {
    this.authService.enserrarSessao();
    this.router.navigate(['/login']);
  }

}
