import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthenticationService } from './authentication.service';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { HttpError } from '../objects/HttpError';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService,private toastr: ToastrService) { }

    

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            let errorMsg = '';
            console.error(err);
            if (err.error instanceof ErrorEvent) {
                console.log('this is client side error');
                errorMsg = `Error: ${err.error.message}`;
              }
              if (err instanceof HttpErrorResponse) {
                switch (err.status) {

                    case HttpError.BadRequest:
                        console.error('Bad Request 400');
                        errorMsg=err.error.message;
                        break;

                    case HttpError.Unauthorized:
                        console.error('Unauthorized 401');
                        errorMsg=err.error.message;
                        this.authenticationService.unauthorizedRedirect();
                        break;

                    case HttpError.NotFound:
                        console.error('Not Found 404');
                        errorMsg=err.error.message;
                        break;

                    case HttpError.TimeOut:
                        console.error('TimeOut 408');
                        errorMsg=err.error.message;
                        this.authenticationService.unauthorizedRedirect();
                        break;

                    case HttpError.Forbidden:
                        console.error('Forbidden 403');
                        errorMsg=err.error.message;
                        this.authenticationService.unauthorizedRedirect();
                        break;

                    case HttpError.InternalServerError:
                        console.error('big bad 500');
                        errorMsg=err.error.message;
                        this.authenticationService.unauthorizedRedirect();
                        break;
                    
                    case HttpError.NotAcceptable:
                        console.error('Not Acceptable 406');
                        errorMsg=err.error.message;
                        break;

                    case HttpError.Conflict:
                        console.error('Conflict 409');
                        errorMsg=err.error.message;
                        this.authenticationService.unauthorizedRedirect();
                        break;

                    default:
                        errorMsg=err.error.message;
                        console.error(errorMsg);
                }
            }
            
            return throwError(err.error);
        }))
    }
}