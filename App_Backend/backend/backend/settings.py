import os
from datetime import timedelta
from pathlib import Path

# Build paths inside the project like this: BASE_DIR / 'subdir'.
BASE_DIR = Path(__file__).resolve().parent.parent

# Quick-start development settings - unsuitable for production
# See https://docs.djangoproject.com/en/4.1/howto/deployment/checklist/

# SECURITY WARNING: keep the secret key used in production secret!
SECRET_KEY = 'django-insecure-+v474quf=$^s071+tul&0t1wa^*!f+y8jfyhq30^k3k7)gq6g&'

# SECURITY WARNING: don't run with debug turned on in production!
DEBUG = True

ALLOWED_HOSTS = os.environ.get('DJANGO_ALLOWED_HOSTS', default='*').split(" ")
if '*' not in ALLOWED_HOSTS and (_domain_name := os.environ.get('NGINX_DOMAIN_NAME')) is not None:
    ALLOWED_HOSTS.append(_domain_name)

# Application definition
DJANGO_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles'
]

EXTERNAL_APPS = [
    'allauth',
    'allauth.account',
    'allauth.socialaccount',
    'dj_rest_auth',
    'dj_rest_auth.registration',
    'drf_spectacular',
    'rest_framework'
]

INTERNAL_APPS = [
    'apps.main',
    'apps.users'
]

INSTALLED_APPS = EXTERNAL_APPS + DJANGO_APPS + INTERNAL_APPS

MIDDLEWARE = [
    'django.middleware.security.SecurityMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
]

ROOT_URLCONF = 'backend.urls'

TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [],
        'APP_DIRS': True,
        'OPTIONS': {
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        },
    },
]

WSGI_APPLICATION = 'backend.wsgi.application'

# List of available databases for project (sqlite for development, postgres for deployment inside Docker)
AVAILABLE_DATABASES = {
    'sqlite': {
        'default': {
            'ENGINE': 'django.db.backends.sqlite3',
            'NAME': BASE_DIR / 'db.sqlite3',
        }
    },
    'postgres': {
        'default': {
            'ENGINE': 'django.db.backends.postgresql',
            'NAME': 'postgres',
            'USER': 'postgres',
            'PASSWORD': 'postgres',
            'HOST': 'db',
            'PORT': 5432
        }
    }
}

# Selected database
DATABASES = AVAILABLE_DATABASES[os.environ.get('DJANGO_DB_TYPE', 'sqlite')]

# Password validation
AUTH_PASSWORD_VALIDATORS = []

# Internationalization
LANGUAGE_CODE = 'ru-ru'

TIME_ZONE = 'Europe/Moscow'

USE_I18N = True

USE_TZ = True

# Static files (CSS, JavaScript, Images)
STATIC_URL = 'api/static/'
STATIC_ROOT = BASE_DIR / 'static'

# Default primary key field type
DEFAULT_AUTO_FIELD = 'django.db.models.BigAutoField'

# Custom user model
AUTH_USER_MODEL = 'users.CustomUser'

# Django REST Framework settings
REST_FRAMEWORK = {
    'DEFAULT_SCHEMA_CLASS': 'drf_spectacular.openapi.AutoSchema',
    'DEFAULT_AUTHENTICATION_CLASSES': [
        'rest_framework_simplejwt.authentication.JWTAuthentication'
    ],
    'DEFAULT_PERMISSION_CLASSES': [
        'rest_framework.permissions.IsAuthenticated'
    ]
}
if DEBUG:
    # Add basic auth for OpenAPI convenience
    REST_FRAMEWORK['DEFAULT_AUTHENTICATION_CLASSES'] += [
        'rest_framework.authentication.BasicAuthentication'
    ]

# dj-rest-auth settings
REST_USE_JWT = True
REST_AUTH_TOKEN_MODEL = None
REST_AUTH_SERIALIZERS = {
    'USER_DETAILS_SERIALIZER': 'apps.users.serializers.CustomUserDetailsSerializer'
}
REST_AUTH_REGISTER_SERIALIZERS = {
    'REGISTER_SERIALIZER': 'apps.users.serializers.CustomRegisterSerializer'
}

# JWT auth settings
SIMPLE_JWT = {
    'ACCESS_TOKEN_LIFETIME': timedelta(hours=24),
    'REFRESH_TOKEN_LIFETIME': timedelta(days=180)
}

# Allauth settings
ACCOUNT_ADAPTER = 'apps.users.adapter.CustomAccountAdapter'
ACCOUNT_EMAIL_REQUIRED = True
ACCOUNT_EMAIL_VERIFICATION = 'none'
ACCOUNT_UNIQUE_EMAIL = True

# OpenAPI settings
SPECTACULAR_SETTINGS = {
    'TITLE': 'Backend API',
    'VERSION': '1.0.0',
    'SCHEMA_PATH_PREFIX': '/api',
    'COMPONENT_SPLIT_REQUEST': True  # To fix JWT token serializers
}
