import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { catchError, map, takeUntil } from 'rxjs/operators';

@Injectable()
export class IntercepterService implements HttpInterceptor {
    error: any;
    private pendingHTTPRequests$ = new Subject<void>();

    constructor(private router: Router) {
    }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        request = request.clone({
            setHeaders: {
                'Access-Control-Expose-Headers': '*',
            }
        });
        return next.handle(request)
            .pipe(
                map(event => {
                    if (event instanceof HttpResponse) {
                    }
                    return event;
                }),
                catchError((error: HttpErrorResponse) => {
                    // this.showError(error);
                    throw error;
                }),
                takeUntil(this.onCancelPendingRequests())
            );
    }
    public cancelPendingRequests() {
        this.pendingHTTPRequests$.next();
        // this.loaderService.hide();
    }

    public onCancelPendingRequests() {
        return this.pendingHTTPRequests$.asObservable();
    }
}
