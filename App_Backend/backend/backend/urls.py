from django.conf import settings
from django.contrib import admin
from django.urls import path, include
from drf_spectacular.views import SpectacularAPIView, SpectacularSwaggerView


urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/auth/', include('apps.users.urls')),
    path('api/app/', include('apps.main.urls')),
]

if settings.DEBUG:
    # Swagger
    urlpatterns += [
        path('schema/raw/', SpectacularAPIView.as_view(), name='schema'),
        path('schema/swagger/', SpectacularSwaggerView.as_view(url_name='schema'), name='swagger')
    ]
